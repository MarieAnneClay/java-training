package java.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.model.Computer;
import java.util.ValidationComputer;

public class ComputerValidator implements Validator {

    @Override
    public boolean supports(Class<?> computer) {
        return Computer.class.equals(computer);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Computer computer = (Computer) target;

        if (ValidationComputer.validationName(computer.getName()) != null) {
            errors.rejectValue("name", ValidationComputer.validationName(computer.getName()));
        }

        if (computer.getIntroduced() != null) {
            if (ValidationComputer.validationDate(computer.getIntroduced().toString()) != null) {
                errors.rejectValue("introduced", ValidationComputer.validationDate(computer.getIntroduced().toString()));
            }
        }

        if (computer.getDiscontinued() != null) {
            if (ValidationComputer.validationDate(computer.getDiscontinued().toString()) != null) {
                errors.rejectValue("discontinued", ValidationComputer.validationDate(computer.getDiscontinued().toString()));
            }
        }

        if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {
            if (ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()) != null) {
                errors.rejectValue("introduced", ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()));
                errors.rejectValue("discontinued", ValidationComputer.validationIntroducedBeforeDiscontinued(computer.getIntroduced(), computer.getDiscontinued()));
            }
        }

    }

}
