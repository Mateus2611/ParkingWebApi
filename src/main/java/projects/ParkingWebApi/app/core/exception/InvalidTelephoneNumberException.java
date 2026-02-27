package projects.ParkingWebApi.app.core.exception;

public class InvalidTelephoneNumberException extends RuntimeException{

    public InvalidTelephoneNumberException(String telephone) {
        super("The telephone '" + telephone + "' is invalid. Because don't respect Brazilian standard.");
    }
}
