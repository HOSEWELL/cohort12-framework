package app.bean;

import app.dao.SchoolDao;
import app.model.Course;
import app.model.School;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class SchoolBean {

    @Inject
    private SchoolDao schoolDao;

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.SCHOOL)
    public Validate<School> validate;

    public boolean save(School school){
        if (validate.process(school)) {
            schoolDao.save(school);
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

}
