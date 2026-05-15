package app.dao;

import app.model.School;
import jakarta.enterprise.context.Dependent;

import java.util.List;

@Dependent
public class SchoolDao extends GenericDao<School, Integer> {

    public List<School> list(School filter){
       return getEm().createQuery("SELECT s FROM School s " +
            "WHERE s.schoolName LIKE :schoolName", School.class)
                .setParameter("schoolName", filter.getSchoolName())
                .getResultList();
    }
}
