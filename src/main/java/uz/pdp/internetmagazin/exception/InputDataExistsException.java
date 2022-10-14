package uz.pdp.internetmagazin.exception;

public class InputDataExistsException extends RuntimeException  {
    public InputDataExistsException(String massage) {
        super(massage);
    }
}
