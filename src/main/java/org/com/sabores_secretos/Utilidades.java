package org.com.sabores_secretos;

public class Utilidades {

    public static boolean validarContrasena(String contrasena) {
        
        if (contrasena.length() < 8) {
            return false;
        }

        boolean contieneMinuscula = false;
        boolean contieneMayuscula = false;
        boolean contieneDigito = false;

        for (char caracter : contrasena.toCharArray()) {
            if (Character.isLowerCase(caracter)) {
                contieneMinuscula = true;
            } else if (Character.isUpperCase(caracter)) {
                contieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                contieneDigito = true;
            }

            if (contieneMinuscula && contieneMayuscula && contieneDigito) {
                break;
            }
        }
        return contieneMinuscula && contieneMayuscula && contieneDigito;
    }


}
