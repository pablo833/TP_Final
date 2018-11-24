package COMMONS;

public class Utils {


    public static boolean isNumeric(String cadena) {
        try {
            Integer.valueOf(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isDouble(String cadena) {
        try {
            Double.valueOf(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
