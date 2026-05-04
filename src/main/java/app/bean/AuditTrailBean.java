package app.bean;

import app.dao.AuditTrailDao;
import app.model.AuditTrail;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.util.List;

@Singleton
public class AuditTrailBean {

    @Inject
    private AuditTrailDao auditTrailDao;

    public void save(AuditTrail auditTrail){
        auditTrailDao.save(auditTrail);
    }

    public List<AuditTrail> list(AuditTrail filter){
        return auditTrailDao.findAll();
    }
}
