package zw.co.sbs.steward.freelancer.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zw.co.sbs.steward.freelancer.domain.Accounts;
import zw.co.sbs.steward.freelancer.domain.ErrorManager;
import zw.co.sbs.steward.freelancer.domain.User;
import zw.co.sbs.steward.freelancer.repository.AccountsRepository;
import zw.co.sbs.steward.freelancer.repository.AuthRepository;


import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@Component("accountService")
@Transactional
public  class  AccountsServiceImpl implements AccountService {
    @Autowired
    private   AccountsRepository repository;
    @Autowired
    private AuthRepository authRepository;
    public AccountsServiceImpl(AccountsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Accounts save(Accounts entity) {
      return   repository.save(entity);
    }

    @Override
    public Response saveAccount(Accounts entity) {

     Accounts entityRecord =   repository.save(entity);

     if(entityRecord!=null){

         ErrorManager manager = new ErrorManager();
         manager.setResponseCode("00");
         manager.setDescription("Successful record has been created");
         manager.setMessage("You have logged in");
         Response response  = responseHandler(Response.Status.OK,manager);
         return response;
     }else{
         ErrorManager manager = new ErrorManager();
         manager.setResponseCode("091");
         manager.setDescription("Failed to create account");
         Response response  = responseHandler(Response.Status.EXPECTATION_FAILED,manager);
         return response;
     }
    }

    @Override
    public Accounts findOne(String id) {
     List<Accounts> list  =  repository.findAll();

     Accounts accounts = list.stream().filter((account) -> account.getAccountNumber() == id).findAny().get();


        return  accounts;
    }
    protected Response responseHandler(Response.Status status, ErrorManager response) {

        return Response.status(status).entity(response).build();
    }
    @Override
    public ResponseEntity<Response> login(User user) {

        Long count = authRepository.findAll().stream()
                .filter(s->user.getUsername().equals(s.getUsername()))
                .filter(x->user.getPassword().equals(x.getPassword())).count();
               if(count==1){

                   ErrorManager manager = new ErrorManager();
                   manager.setResponseCode("00");
                   manager.setDescription("Success");
                   manager.setMessage("You have logged in");
                   Response response  = responseHandler(Response.Status.OK,manager);
                   return new ResponseEntity<Response>(response, HttpStatus.OK);
               }else{
                   ErrorManager manager = new ErrorManager();

                   manager.setResponseCode("23");
                   manager.setDescription("Failed");
                   Response response  = responseHandler(Response.Status.EXPECTATION_FAILED,manager);
                   return new ResponseEntity<Response>(response, HttpStatus.OK);
               }


    }

    @Override
    public Accounts getById(Serializable id) {
        return repository.findOne((Long) id);
    }



    @Override
    public List<Accounts> getAll() {
        return (List<Accounts>) repository.findAll();
    }

    @Override
    public void delete(Serializable id) {
       repository.delete((Long) id);
    }
}