package app.utility.validation;

import app.model.Trainee;
import app.model.Trainer;
import jakarta.enterprise.context.ApplicationScoped;

@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINER)
@ValidateNationality
@ApplicationScoped
public class ValidateTrainer implements Validate<Trainer> {
    private int validationCounter = 0;



    @Override
    public void printValidation() {
        System.out.println("Validate Trainer");
    }

    public boolean process(Trainer trainer){
        System.out.println("Validating Trainer(Teacher) Validation Number: " + validationCounter++);
        return trainer != null
               && trainer.getName() != null
                && !trainer.getName().toLowerCase().contains("pamela")
                &&  !trainer.getName().toLowerCase().contains("gideon")
                &&  !trainer.getName().toLowerCase().contains("mathew")
                &&  !trainer.getName().toLowerCase().contains("fian");
    }
}
