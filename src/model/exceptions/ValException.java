package model.exceptions;

import java.util.HashMap;
import java.util.Map;
public class ValException extends RuntimeException {

    private Map<String, String> errors = new HashMap<>();

    public ValException(String msg) {
        super(msg);
    }

    public Map<String, String> getErros() {
        return errors;
    }

    public void addError(String fieldName, String errorMessage) {
        errors.put(fieldName, errorMessage);
    }

}
