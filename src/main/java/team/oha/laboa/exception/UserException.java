package team.oha.laboa.exception;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/1
 * @modified
 */
public class UserException extends BaseException {
    private String username;

    public String getUsername() {
        return username;
    }

    public UserException(String username) {
        this.username = username;
    }

    public UserException(String message, String username) {
        super(message);
        this.username = username;
    }

    public UserException(String message, Throwable cause, String username) {
        super(message, cause);
        this.username = username;
    }

    public UserException(Throwable cause, String username) {
        super(cause);
        this.username = username;
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String username) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.username = username;
    }
}
