package zw.co.sbs.steward.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zw.co.sbs.steward.freelancer.domain.*;
import zw.co.sbs.steward.freelancer.repository.AuditRepository;
import zw.co.sbs.steward.freelancer.repository.AuthRepository;
import zw.co.sbs.steward.freelancer.repository.DeviceLoginRepository;
import zw.co.sbs.steward.freelancer.repository.DeviceRepository;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Component("authService")
@Transactional
public class UserServiceImpl implements UserServices {

    @Autowired
    AuthRepository authRepository;
    @Autowired
   DeviceRepository deviceRepository;

    @Autowired
    DeviceLoginRepository deviceLoginRepository;

    @Autowired
    AuditRepository auditRepository;

    protected Response handleResponse(String description) {


        ErrorManager error = new ErrorManager();
        error.setResponseCode("404");
        error.setDescription(description);
        return errorResponse(Response.Status.EXPECTATION_FAILED, error);
    }

    protected Response errorResponse(Response.Status status, ErrorManager error) {

        return Response.status(status).header("X-Has-Error", true).entity(error).build();
    }

    protected Response handleException(Throwable e) {


        ErrorManager error = new ErrorManager();
        error.setResponseCode("999");
        error.setDescription("Sorry an exception occured");
        error.setMessage(e.getMessage());
        return errorResponse(Response.Status.INTERNAL_SERVER_ERROR, error);
    }

    @Override
    public User save(User entity) {
        return authRepository.save(entity);
    }

    @Override
    public User getById(Serializable id) {
        return authRepository.findOne((Long) id);
    }

    @Override
    public List<User> getAll() {
        return authRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
 authRepository.delete((Long) id);
    }

    @Override
    public User findOne(String id) {
        return null;
    }

    @Override
    public Response login(User user) {

        List<User> list = authRepository.findAll();
        long userAuth = list.stream()
                .filter(x -> user.getUsername().equals(x.getUsername()))
                .filter(y -> user.getPassword().equals(y.getPassword()))
                .count();

        if(userAuth==1)
        {


   long deviceCheck = deviceLoginRepository.findAll().stream()
                        .filter(x->user.getDeviceId()==x.getDeviceId())
                        .filter(z->user.getUsername().equals(z.getUserId()))
                        .filter(x->"LoggedIn".equals(x.getStatus()))
                        .count();
   if(deviceCheck>0){

       return handleResponse("Cannot proceed.Device with username provided already loggedIn.");
   }else {

       DeviceLogins deviceLogin = new DeviceLogins();
       deviceLogin.setDeviceId(user.getDeviceId());
       deviceLogin.setStatus("LoggedIn");
       deviceLogin.setUserId(user.getUsername());
       deviceLoginRepository.save(deviceLogin);

       return Response.ok(user).build();
   }
        }else {
            return handleResponse("Invalid User");
        }
    }

    @Override
    public Response registerDevice(Devices device) {

      Devices entity =  deviceRepository.save(device);
        if(entity!=null)
        {



            return Response.ok(entity).build();
        }else {
            return handleResponse("Device failed to register");
        }




    }

    @Override
    public void logOutDevice(DeviceLogins entity) {
        long deviceCheck = deviceLoginRepository.findAll().stream()
                .filter(x->entity.getDeviceId()==x.getDeviceId())
                .filter(z->entity.getUserId().equals(z.getUserId()))
                .filter(x->"LoggedIn".equals(x.getStatus()))
                .count();
        if(deviceCheck>0){


            DeviceLogins deviceLogin = new DeviceLogins();
            deviceLogin.setDeviceId(entity.getDeviceId());
            deviceLogin.setStatus("LoggedOut");
            deviceLogin.setUserId(entity.getUserId());
            deviceLoginRepository.save(deviceLogin);

        }
    }

    @Override
    public void saveAudit(Audits audits) {
      auditRepository.save(audits);
    }
}

