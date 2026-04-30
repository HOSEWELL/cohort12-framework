package app.bean;

import app.dao.TraineeDao;
import app.model.Trainee;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class TraineeBean {

    @Inject
    private TraineeDao traineeDao;

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINEE)
    public Validate<Trainee> validate;

    public boolean save(Trainee trainee){
        if (validate.process(trainee)) {
            traineeDao.save(trainee);
            return true;
        }

        return false;
    }

    public List<Trainee> list(Trainee filter){
        return traineeDao.findAll();
    }

}
