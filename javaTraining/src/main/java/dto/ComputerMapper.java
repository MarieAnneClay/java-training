package dto;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Computer;
import util.Validator;
import util.ValidatorException;

public class ComputerMapper {

    public static Computer convertDTOToComputer(ComputerDTO computerDTO) throws ValidatorException{
        Validator.validationDate(computerDTO.getIntroduced());
        Validator.validationDate(computerDTO.getDiscontinued());
        return new Computer.ComputerBuilder()
                .setId(computerDTO.getId() == null ? 0 : Long.parseLong(computerDTO.getId()))
                .setName(computerDTO.getName())
                .setIntroduced(computerDTO.getIntroduced().equals("") ? null : LocalDate.parse(computerDTO.getIntroduced()))
                .setDiscontinued(computerDTO.getDiscontinued().equals("") ? null : LocalDate.parse(computerDTO.getDiscontinued()))
                .setCompanyId(computerDTO.getCompanyId() == "" ? 0 : Long.parseLong(computerDTO.getCompanyId()))
                .build();
    }

    public static ComputerDTO convertComputerToDTO(Computer computer) {
        return new ComputerDTO.ComputerBuilder()
                .setId(Long.toString(computer.getId()))
                .setName(computer.getName())
                .setIntroduced(computer.getIntroduced()== null ? null : computer.getIntroduced().toString())
                .setDiscontinued(computer.getDiscontinued()== null ? null : computer.getDiscontinued().toString())
                .setCompanyId(Long.toString(computer.getCompanyId()))
                .build();
    }
    
    public static ArrayList<ComputerDTO> convertComputersToDTOS (ArrayList<Computer> computers){
        ArrayList<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
        for (Computer computer : computers) {
            computersDTO.add(ComputerMapper.convertComputerToDTO(computer));
        }
        return computersDTO;
    }

}
