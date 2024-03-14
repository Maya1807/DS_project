public class IllegalArgumentException extends Exception{
    public IllegalArgumentException() {
        super("IllegalArgumentException");
    }
    public IllegalArgumentException(String message){
        super(message);
    }
    public IllegalArgumentException(String message, Throwable cause){
        super(message, cause);
    }

}
