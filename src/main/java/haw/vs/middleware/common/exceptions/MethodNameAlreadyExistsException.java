package haw.vs.middleware.common.exceptions;

/**
 * This Exception is thrown if the methodname used as a key in a map is already in the map
 * Prevents the existing key/value in the map to be overwritten
 */
public class MethodNameAlreadyExistsException extends Exception {
    public MethodNameAlreadyExistsException(String message){
        super(message);
    }
}
