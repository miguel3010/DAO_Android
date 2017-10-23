/**
 * TODOS LOS DERECHOS RESERVADOS 2016
 * MIGUEL ÁNGEL CAMPOS COPYRIGHTS © 2016
 */
package com.miguel.dao_android.General;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Miguel Ángel Campos
 */
public class Util {

    private Util() {
        //Constructor       
    }

    /**
     * Verifica si un String es numerico
     *
     * @param k String a verificar
     * @return true si k es numerico
     */
    public static boolean isNumeric(String k) {

        if (k != null && !k.isEmpty()) {
            try {
                int l = Integer.valueOf(k);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     *
     * @return Obtiene la fecha en el formato YY-MM-DD
     */
    public static String getDate() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        return (año + "-" + (mes + 1) + "-" + dia);
    }

    /**
     *
     * @return Obtiene la Hora en el formato HH-MM-SS
     */
    public static String getTime() {
        Calendar fecha = new GregorianCalendar();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        return (hora + ":" + minuto + ":" + segundo);
    }

    /**
     * Validador de email
     *
     * @param email correo electronico a validar
     * @return true = valido<br>
     * false = invalido
     */
    public static boolean validateEmail(String email) {
        if (Util.validateString(email)) {
            char r = email.charAt(0);
            if (r >= 0 + 48 && r <= 9 + 48) {
                return false;
            }
            final String ePattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);

            return m.matches();
        }
        return false;
    }

    public static boolean validatePassword(String pass) {

        if (Util.validateString(pass)) {
            if (pass.length() > 8) {
                int i = 0;
                char[] h = pass.toCharArray();
                int mayus = 0, digits = 0, minus = 0;
                while (i < pass.length()) {
                    if (Character.isDigit(h[i])) {
                        digits++;
                    } else if (Character.isUpperCase(h[i])) {
                        mayus++;
                    } else if (Character.isLowerCase(h[i])) {
                        minus++;
                    }
                    if (mayus > 0 && digits > 0 && minus > 0) {
                        return true;
                    }
                    i++;
                }
            }
        }

        return false;
    }

    public static boolean validateString(String txt) {
        if (txt != null) {
            if (!txt.isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
