package app.utility.validation;


import app.model.Trainee;
import jakarta.enterprise.context.ApplicationScoped;


@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINEE)
@ApplicationScoped
public class ValidateTrainee implements Validate<Trainee> {
    private int validationCounter = 0;



    @Override
    public void printValidation() {
        System.out.println("Validate Trainee");
    }

    public boolean process(Trainee trainee){
        System.out.println("Validating Trainee(Student) - Validation Number: " + validationCounter++);
        return trainee != null
           && trainee.getName() != null
            && !trainee.getName().toLowerCase().contains("john")
            && !trainee.getName().toLowerCase().contains("nancy");
    }
}
