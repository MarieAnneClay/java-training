package Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validator {
	
	private void validationName(String name) throws Exception {
		if (name == null || name.trim().length() == 0)
    		throw new Exception("Le nom du nouveau membre ne peut pas être vide");
	}
	
	private void validationEmail(String email) throws Exception {
		if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "L'addresse email n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "L'addresse email ne peut pas être vide" );
	    }
	}
	
	private void validationBirthdate(String birthdate) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
		if ( birthdate != null && birthdate.trim().length() != 0 ) {
	        try {
	        	fmt.parse(birthdate);
	        } catch (ParseException e) {
	        	throw new Exception( "Le format de la date de naissance n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "La date de naissance ne peut pas être vide" );
	    }
	}
	
	private void validationPromo(String promo) throws Exception {
		
	}

}
