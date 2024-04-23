package io.dodn.springboot.core.api.validation;

public class ValidationUtil {

    private ValidationUtil() {

    }

    public static boolean isUUID(String str){
        return str.matches("[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}");
    }
}
