package zw.co.sbs.steward.freelancer.controller;


import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zw.co.sbs.steward.freelancer.domain.*;
import zw.co.sbs.steward.freelancer.service.AccountService;
import zw.co.sbs.steward.freelancer.service.CustomerService;
import zw.co.sbs.steward.freelancer.service.DocumentService;
import zw.co.sbs.steward.freelancer.service.UserServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;


@RestController
@RequestMapping("api/registrations")
public class RegistrationController {
    final static Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;

    @Autowired
    DocumentService documentService;

    @Autowired
    UserServices userServices;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes("multipart/mixed")
    public Response addCustomers(@RequestBody Customers entity,
                                 @Multipart(value = "idImage", required = false) Attachment idImage,
                                 @Multipart(value = "headshotImage", required = false) Attachment profileImage,
                                 @Multipart(value = "residenceImage", required = false) Attachment residenceImage) throws IOException {
        entity.setDateCreated(ZonedDateTime.now());
        entity.setDateUpdated(ZonedDateTime.now());
        Customers customers = customerService.save(entity);
        Response response = null;
        if (customers != null) {
            Accounts accountEntity = new Accounts();

            accountEntity.setAccountNumber(entity.getAccountNumber());
            accountEntity.setAccountName(customers.getName());
            accountEntity.setAccountType("Savings");
            accountEntity.setDateCreated(ZonedDateTime.now());
            accountEntity.setDateUpdated(ZonedDateTime.now());

            response = accountService.saveAccount(accountEntity);
            logger.debug("Added record to database"+ response.toString());


            Path rootPath = Paths.get("/srv/storage/");
            Path idPhotoPath = rootPath.resolve(entity.getIdNumber() + "-id.jpg");
            Path profilePath = rootPath.resolve(entity.getIdNumber() + "-headshot.jpg");
            Path residencePath = rootPath.resolve(entity.getIdNumber() + "-residence.jpg");
            if (idImage != null) {
                entity.getAttachments().put("idImage", idImage.getObject(InputStream.class));
            }
            if (profileImage != null) {
                entity.getAttachments().put("headshotImage", profileImage.getObject(InputStream.class));
            }
            if (profileImage != null) {
                entity.getAttachments().put("residenceImage", profileImage.getObject(InputStream.class));
            }


           saveToDisk(entity,accountEntity, "idImage", idPhotoPath);
           saveToDisk(entity, accountEntity, "headshotImage", profilePath);
            saveToDisk(entity, accountEntity, "residenceImage", residencePath);

                Audits audits = new Audits();
                audits.setAccountNumber(accountEntity.getAccountNumber());
                audits.setAccountType(accountEntity.getAccountType());
                audits.setCreatedDate(ZonedDateTime.now());
                audits.setUserId(entity.getAgentId());
            logger.debug("Added Audit record to database"+ response.toString());
                userServices.saveAudit(audits);
            }


            return response;
        }

    private void saveToDisk(Customers entity,Accounts accounts, String key, Path path) throws IOException {
        if (entity.getAttachments().containsKey(key)) {
            InputStream stream = (InputStream)entity.getAttachments().get(key);
            Path parentDir = path.getParent();
            java.io.File file = parentDir.toFile();
            if (!file.exists()) {
                file.mkdirs();
            } else {
                Files.deleteIfExists(path);
            }

            Files.copy(stream, path, new CopyOption[0]);

            Documents documents = new Documents();

            documents.setFileUrl(path.toString());
            documents.setAccounts(accounts);
            documents.setDocumentType(key.replace("Image","").toUpperCase());
            documents.setZonedDateTimeCreated(ZonedDateTime.now());
            documents.setZonedDateTimeUpdated(ZonedDateTime.now());
            documentService.save(documents);
            logger.debug("Added Documents to account record to database");
        }

    }




    @RequestMapping(method = RequestMethod.POST,
            value = "/token")
    public ResponseEntity<Response> login(@RequestBody User user) {
        ResponseEntity<Response> response = accountService.login(user);
        return new ResponseEntity<Response>((MultiValueMap<String, String>) response, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/registerDevice")
    public ResponseEntity<Response> registerDevice(@RequestBody Devices devices) {
        Response response = userServices.registerDevice(devices);
        return new ResponseEntity<Response>((MultiValueMap<String, String>) response, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/logout")
    public ResponseEntity<Response> logout(@RequestBody DeviceLogins entity) {
        userServices.logOutDevice(entity);

        return new ResponseEntity<Response>((MultiValueMap<String, String>) Response.ok(), HttpStatus.OK);

    }
}
