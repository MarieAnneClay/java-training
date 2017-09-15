package main.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/** Class of validation method for user entries. */
public class Validator {
  /**
   * Function test if the String null or empty.
   * 
   * @return 0 if true, 1 if false
   * @param string
   *          the string to be tested
   */
  public static final int validationName(final String string) {
    if (string == null || string.trim().length() == 0) {
      System.out.println("Le nom du nouvel ordinateur ne peut pas être vide");
      return 0;
    }
    return 1;
  }

  /**
   * Function test if the date format is conform. and verify if null or empty.
   * 
   * @param date
   *          the date to be tested
   */
  public final void validationDate(final String date) {
    SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
    if (date != null && date.trim().length() != 0) {
      try {
        fmt.parse(date);
      } catch (ParseException e) {
        System.out
            .println("Le format de la date de naissance n'est pas valide.");
      }
    } else {
      System.out.println("La date de naissance ne peut pas être vide");
    }
  }

  /**
   * Performs date validation and conversion of String to LocalDate.<br/>
   * If valid, then date string is converted to LocalDate.
   * 
   * @param input
   *          Date string in format.
   * @return the LocalDate.
   */
  public static LocalDate parseStringToLocalDate(String date) {
    if (date == null || date.trim().length() == 0) {
      throw new RuntimeException("Date input can't be null.");
    }

    DateTimeFormatter formatter = null;
    LocalDate localDate = null;

    try {
      formatter = new DateTimeFormatterBuilder().appendPattern("[dd/MM/yyyy]")
          .appendPattern("[dd/MM/yy]").appendPattern("[dd/M/yyyy]")
          .appendPattern("[dd/M/yy]").appendPattern("[d/MM/yyyy]")
          .appendPattern("[d/MM/yy]").appendPattern("[d/M/yyyy]")
          .appendPattern("[d/M/yy]").toFormatter();

      localDate = LocalDate.parse(date.trim(), formatter);
    } catch (Exception e) {
      throw new RuntimeException("Date is invalid. "
          + "It could be out of bounds. Please correct the format. "
          + "Accepted formats are dd/MM/yyyy, dd/MM/yy, dd/M/yyyy, dd/M/yy "
          + "d/MM/yyyy, d/MM/yy, d/M/yyyy, d/M/yy. " + e);
    }

    return localDate;
  }

}
