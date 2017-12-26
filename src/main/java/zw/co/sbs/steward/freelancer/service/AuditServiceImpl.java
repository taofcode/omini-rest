package zw.co.sbs.steward.freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import zw.co.sbs.steward.freelancer.domain.Audits;
import zw.co.sbs.steward.freelancer.repository.AuditRepository;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
@Component("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {
    @Autowired
    AuditRepository auditRepository;

    @Override
    public Audits save(Audits entity) throws IOException {
        return auditRepository.save(entity);
    }

    @Override
    public Audits getById(Serializable id) {
        return auditRepository.findOne((Long) id);
    }

    @Override
    public List<Audits> getAll() {
        return auditRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
    auditRepository.delete((Long) id);
    }
}
