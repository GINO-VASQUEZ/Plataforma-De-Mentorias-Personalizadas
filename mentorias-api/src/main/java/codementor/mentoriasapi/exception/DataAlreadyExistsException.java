package codementor.mentoriasapi.exception;

public class DataAlreadyExistsException extends RuntimeException{

    public DataAlreadyExistsException(String message){
        super(message);
    }
}
