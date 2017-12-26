package zw.co.sbs.steward.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zw.co.sbs.steward.freelancer.domain.Customers;
import zw.co.sbs.steward.freelancer.repository.CustomersRepository;

import java.io.Serializable;
import java.util.List;

@Component("customerService")
@Transactional
public class CustomerServiceImpl  implements CustomerService {
    @Autowired
    private CustomersRepository repository;


    @Override
    public Customers findOne(String id) {
        List<Customers> list  =  repository.findAll();

        Customers Customers = list.stream().filter((entity) -> entity.getIdNumber() == id).findAny().get();


        return  Customers;
    }

    @Override
    public Customers getById(Serializable id) {
        return repository.findOne((Long) id);
    }

    @Override
    public Customers save(Customers entity) {
        return   repository.save(entity);
    }


    @Override
    public List<Customers> getAll() {
        return (List<Customers>) repository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        repository.delete((Long) id);
    }
}