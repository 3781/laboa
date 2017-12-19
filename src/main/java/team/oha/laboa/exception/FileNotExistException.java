package team.oha.laboa.exception;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/19
 * @modified
 */
public class FileNotExistException extends FileException{
    public FileNotExistException() {
    }

    public FileNotExistException(String message) {
        super(message);
    }

    public FileNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotExistException(Throwable cause) {
        super(cause);
    }

    public FileNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
