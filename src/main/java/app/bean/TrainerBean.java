package app.bean;

import app.dao.TrainerDao;
import app.model.Trainer;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class TrainerBean {

    @Inject
    private TrainerDao trainerDao;

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINER)
    public Validate<Trainer> validate;

    public boolean save(Trainer trainer){
        if (validate.process(trainer)) {
            trainerDao.save(trainer);
            return true;
        }

        return false;
    }

    public List<Trainer> list(Trainer filter){
        return trainerDao.findAll();
    }

}
