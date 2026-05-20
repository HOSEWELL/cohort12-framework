package app.bean;

import app.dao.SchoolDao;
import app.model.AuditTrail;
import app.model.School;
import app.model.Status;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class SchoolBean {


    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Inject
    private SchoolDao schoolDao;

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.SCHOOL)
    public Validate<School> validate;

    public boolean save(School school){
        if (validate.process(school)) {
            auditTrailEvent.fire(new AuditTrail("Creating school: "
                    + school.getSchoolName()));

            if (school.getStatus() == null)
                school.setStatus(Status.ACTIVE);

            schoolDao.save(school);
            return true;
        }

        return false;
    }

    public School findById(Long id){
        return schoolDao.findById(id);
    }

    public boolean delete(Long id){
        if (id > 0 ) {
            auditTrailEvent.fire(new AuditTrail("School Deleted, ID: " + id));
            schoolDao.delete(id);
            return true;
        }

        return false;
    }

    public List<School> list(School filter){
        return schoolDao.findAll();
    }

    public boolean exist(School check) {
        if (check == null || check.getSchoolName() == null)
            return false;

        List<School> schools = schoolDao.findAll();

        boolean schoolFound = false;

        for (School school : schools){
            if (schoolFound)
                break;

            schoolFound = school.getSchoolName()
                    .equalsIgnoreCase(check.getSchoolName());
        }

        return schoolFound;

    }

    public void updateStatus(School school) throws Exception{

        if (school == null || school.getId() == null)
            throw new RuntimeException("Invalid school details!!");

        if (school.getStatus() == null)
            throw new RuntimeException("Please provide the status to update!!");

        School schoolToUpdate = schoolDao.findById(school.getId());

        if (schoolToUpdate.getStatus() == school.getStatus())
            throw new RuntimeException("School is already "
                    + school.getStatus().getName());

        schoolToUpdate.setStatus(school.getStatus());
        schoolDao.save(school);


    }

}