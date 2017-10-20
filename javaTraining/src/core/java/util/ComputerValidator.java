package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Computer;

public class ComputerValidator implements Validator {

    @Override
    public boolean supports(Class<?> computer) {
        return Computer.class.equals(computer);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Computer computer = (Computer) target;

        if (util.Validator.validationName(computer.getName()) != null) {
            errors.rejectValue("name", util.Validator.validationName(computer.getName()));
        }

        if (computer.getIntroduced() != null) {
            if (util.Validator.validationDate(computer.getIntroduced().toString()) != null) {
                errors.rejectValue("introduced", util.Validator.validationDate(computer.getIntroduced().toString()));
            }
        }

        if (computer.getDiscontinued() != null) {
            if (util.Validator.validationDate(computer.getDiscontinued().toString()) != null) {
                errors.rejectValue("discontinued", util.Validator.validationDate(computer.getDiscontinued().toString()));
            }
        }

        if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {
            if (util.Validator.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()) != null) {
                errors.rejectValue("introduced", util.Validator.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()));
                errors.rejectValue("discontinued", util.Validator.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()));
            }
        }

    }

}
