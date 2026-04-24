package app.utility.validation;

import jakarta.enterprise.context.ApplicationScoped;

@ValidatorQualifier(ValidatorQualifier.ValidationChoice.SCHOOL)
@ApplicationScoped
public class ValidateSchool implements Validate{
    @Override
    public boolean name(String name) {
        return name != null && name.contains("Nairobi");
    }
}
