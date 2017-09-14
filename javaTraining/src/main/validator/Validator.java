package main.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
	
	public static int validationName(String name) {
		if (name == null || name.trim().length() == 0) {
			System.out.println("Le nom du nouvel ordinateur ne peut pas être vide");
			return 0;
		}
		return 1;
	}
	
	public void validationBirthdate(String birthdate) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
		if ( birthdate != null && birthdate.trim().length() != 0 ) {
	        try {
	        	fmt.parse(birthdate);
	        } catch (ParseException e) {
	        	System.out.println("Le format de la date de naissance n'est pas valide.");
	        }
	    } else {
	        System.out.println("La date de naissance ne peut pas être vide");
	    }
	}

}
