package app.utility.validation;

import app.model.School;
import jakarta.enterprise.context.ApplicationScoped;

@ValidatorQualifier(ValidatorQualifier.ValidationChoice.SCHOOL)
@ApplicationScoped
public class ValidateSchool implements Validate<School>{

    @Override
    public void printValidation() {
        System.out.println("Validate School");
    }

    @Override
    public boolean process(School school) {
        return school != null
            && school.getSchoolName() != null
            && school.getSchoolName().contains("Nairobi");
    }
}
