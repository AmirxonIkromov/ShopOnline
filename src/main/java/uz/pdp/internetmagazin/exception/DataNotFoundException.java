package uz.pdp.internetmagazin.exception;

public class DataNotFoundException extends RuntimeException {

  public DataNotFoundException(String massage){
      super(massage);
  }
}
