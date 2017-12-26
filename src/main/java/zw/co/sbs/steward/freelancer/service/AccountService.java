package zw.co.sbs.steward.freelancer.service;

import org.springframework.http.ResponseEntity;
import zw.co.sbs.steward.freelancer.domain.Accounts;
import zw.co.sbs.steward.freelancer.domain.User;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public interface AccountService extends GenericDao<Accounts> {
    @Override
    Accounts getById(Serializable id);

    @Override
    Accounts save(Accounts entity);


    Response saveAccount(Accounts entity);
    Accounts findOne(String id);

    ResponseEntity<Response> login(User user);
}
