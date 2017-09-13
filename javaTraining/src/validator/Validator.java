package validator;

public class Validator {
	
	public static int validationName(String name) {
		if (name == null || name.trim().length() == 0) {
			System.out.println("Le nom du nouvel ordinateur ne peut pas être vide");
			return 0;
		}
		return 1;
	}

}
