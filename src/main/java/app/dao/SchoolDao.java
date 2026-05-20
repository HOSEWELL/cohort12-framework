package app.dao;

import app.model.School;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class SchoolDao extends GenericDao<School, Long> {

    public List<School> list(School filter){
       return getEm().createQuery("SELECT s FROM School s " +
            "WHERE s.schoolName LIKE :schoolName", School.class)
                .setParameter("schoolName", filter.getSchoolName())
                .getResultList();
    }

}
