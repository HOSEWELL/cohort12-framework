package app.bean;

import app.dao.TrainerDao;
import app.model.AuditTrail;
import app.model.Trainer;
import app.utility.validation.Validate;
import app.utility.validation.ValidatorQualifier;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class TrainerBean {

    @Inject
    private Event<AuditTrail> auditTrailEvent;

    @Inject
    private TrainerDao trainerDao;

    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINER)
    public Validate<Trainer> validate;

    public boolean save(Trainer trainer){
        if (validate.process(trainer)) {
            auditTrailEvent.fire(new AuditTrail("Creating Trainer: "
                + trainer.getName()));
            trainerDao.save(trainer);
            return true;
        }

        return false;
    }

    public List<Trainer> list(Trainer filter){
        return trainerDao.findAll();
    }

}
