package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import DTO.ComputerDTO;
import model.Computer;

public class ComputerDTOValidator implements Validator {

    @Override
    public boolean supports(Class computerDTO) {
        return Computer.class.equals(computerDTO);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerDTO computerDTO = (ComputerDTO) target;

        if (util.Validator.validationName(computerDTO.getName()) != null) {
            errors.rejectValue("name", util.Validator.validationName(computerDTO.getName()));
        }

        if (util.Validator.validationDate(computerDTO.getIntroduced()) != null) {
            errors.rejectValue("introduced", util.Validator.validationDate(computerDTO.getIntroduced()));
        }

        if (util.Validator.validationDate(computerDTO.getDiscontinued()) != null) {
            errors.rejectValue("discontinued", util.Validator.validationDate(computerDTO.getDiscontinued()));
        }

    }

}
