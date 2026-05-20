package app.bean;

import app.dao.CampusDao;
import app.model.AuditTrail;
import app.model.Campus;
import app.model.School;
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

    public Campus save(Campus campus){
        auditTrailEvent.fire(new AuditTrail("Creating campus: "
            + campus.getName()));

        if (campus.getSchoolId() == null
                && campus.getSchool() == null)
            return campus;

        if (campus.getSchoolId() != null)
            campus.setSchool(campusDao.getEm()
                .getReference(School.class, campus.getSchoolId()));

        campus = campusDao.save(campus);

        return campus;
    }

    public boolean delete(Long id){
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
