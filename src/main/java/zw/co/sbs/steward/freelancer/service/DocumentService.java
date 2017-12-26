package zw.co.sbs.steward.freelancer.service;

import zw.co.sbs.steward.freelancer.domain.Documents;

import java.io.Serializable;

public interface DocumentService  extends GenericDao<Documents> {
    @Override
    Documents getById(Serializable id);

    @Override
    Documents save(Documents eontity);

    Documents findOne(String id);
}