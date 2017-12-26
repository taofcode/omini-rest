package zw.co.sbs.steward.freelancer.service;

import zw.co.sbs.steward.freelancer.domain.Customers;

import java.io.Serializable;

public interface CustomerService  extends GenericDao<Customers> {
    @Override
    Customers getById(Serializable id);

    @Override
    Customers save(Customers entity);

    Customers findOne(String id);
}
