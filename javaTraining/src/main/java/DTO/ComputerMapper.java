package DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import model.Computer;
import util.Validator;
import util.ValidatorException;

public class ComputerMapper {
    private static Logger LOGGER = Logger.getLogger(ComputerMapper.class.getName());

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
        LOGGER.warning(computer.getId()== null ? "null" : "NotNull");
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
        LOGGER.warning(Integer.toString(computersDTO.size()));
        for (Computer computer : computers) {
            computersDTO.add(ComputerMapper.convertComputerToDTO(computer));
        }
        return computersDTO;
    }

}
