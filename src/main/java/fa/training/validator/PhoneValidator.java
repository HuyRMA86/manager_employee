package fa.training.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if(phone == null) {
            return false;
        }
        return phone.matches("^\\d{10}$");
    }
}
