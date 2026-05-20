package app.dao;

import app.model.Trainee;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TraineeDao extends GenericDao<Trainee, Long> {

    public List<Trainee> list(Trainee filter) {
        String query = "SELECT t FROM Trainee t WHERE 1=1";

        //if (filter.getName() != null)
            //bind parameter name for where clause

        //if (filter.getNationalId() != null)
            //bind parameter nationalId for where clause

        List<Trainee> trainees = getEm().createQuery(query, Trainee.class)
            .getResultList();

        return trainees;
    }

    public Trainee listByName(String traineeName) {
        return getEm().createQuery("SELECT t FROM Trainee t " +
            "WHERE t.name=:traineeName", Trainee.class)
            .setParameter("traineeName", traineeName)
            .setFirstResult(51)
            .setMaxResults(50)
            .getSingleResult();
    }

    public List<Trainee> listByNameAndNationalId(String traineeName,
         String nationalId) {
        return getEm().createQuery("SELECT t FROM Trainee t " +
            "WHERE t.name=:traineeName AND t.nationalId=:nationalId",
            Trainee.class)
            .setParameter("traineeName", traineeName)
            .setParameter("nationalId", nationalId)
            .getResultList();
    }

}
