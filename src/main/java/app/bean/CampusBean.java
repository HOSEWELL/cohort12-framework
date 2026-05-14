package app.bean;

import app.dao.CampusDao;
import app.model.AuditTrail;
import app.model.Campus;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class CampusBean {

    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Inject
    private CampusDao campusDao;

    public boolean save(Campus campus){
        auditTrailEvent.fire(new AuditTrail("Creating campus: "
            + campus.getName()));
        campusDao.save(campus);
        return true;
    }

    public boolean delete(Integer id){
        if (id > 0 ) {
            auditTrailEvent.fire(new AuditTrail("Campus Deleted, ID: " + id));
            campusDao.delete(id);
            return true;
        }

        return false;
    }

    public List<Campus> list(Campus filter){
        return campusDao.findAll();
    }

}
