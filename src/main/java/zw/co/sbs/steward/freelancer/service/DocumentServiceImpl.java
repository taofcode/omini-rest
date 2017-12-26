package zw.co.sbs.steward.freelancer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zw.co.sbs.steward.freelancer.domain.Documents;
import zw.co.sbs.steward.freelancer.repository.DocumentRepository;

import java.io.Serializable;
import java.util.List;


@Component("documentService")
@Transactional
public  class DocumentServiceImpl implements DocumentService {
    @Autowired
    private   DocumentRepository repository;

    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Documents save(Documents entity) {
      return
              repository.save(entity);
    }

    @Override
    public Documents findOne(String id) {
     List<Documents> list  =  repository.findAll();

     Documents accounts = list.stream().filter((account) -> account.getDocumentType() == id).findAny().get();


        return  accounts;
    }

    @Override
    public Documents getById(Serializable id) {
        return repository.findOne((Long) id);
    }



    @Override
    public List<Documents> getAll() {
        return (List<Documents>) repository.findAll();
    }

    @Override
    public void delete(Serializable id) {
       repository.delete((Long) id);
    }
}