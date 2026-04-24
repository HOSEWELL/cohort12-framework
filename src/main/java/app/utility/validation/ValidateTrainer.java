package app.utility.validation;

import jakarta.enterprise.context.ApplicationScoped;

@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINER)
@ApplicationScoped
public class ValidateTrainer implements Validate {
    private int validationCounter = 0;

    public boolean name(String name){
        System.out.println("Validating Trainer(Teacher) Validation Number: " + validationCounter++);
        return name != null &&
                !name.toLowerCase().contains("pamela")
                &&  !name.toLowerCase().contains("gideon")
                &&  !name.toLowerCase().contains("mathew")
                &&  !name.toLowerCase().contains("fian");
    }
}
