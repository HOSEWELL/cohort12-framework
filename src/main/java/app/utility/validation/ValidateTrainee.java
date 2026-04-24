package app.utility.validation;


import jakarta.enterprise.context.ApplicationScoped;


@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TRAINEE)
@ApplicationScoped
public class ValidateTrainee implements Validate{
    private int validationCounter = 0;

    public boolean name(String name){
        System.out.println("Validating Trainee(Student) - Validation Number: " + validationCounter++);
        return name != null && !name.toLowerCase().contains("john")
            && !name.toLowerCase().contains("nancy");
    }
}
