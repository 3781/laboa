package team.oha.laboa.exception;

import java.text.MessageFormat;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/1
 * @modified
 */
public class UnknownUserException extends UserException {
    public UnknownUserException(String username) {
        super(username);
    }

    public UnknownUserException(String message, String username) {
        super(message, username);
    }

    public UnknownUserException(String message, Throwable cause, String username) {
        super(message, cause, username);
    }

    public UnknownUserException(Throwable cause, String username) {
        super(cause, username);
    }

    public UnknownUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String username) {
        super(message, cause, enableSuppression, writableStackTrace, username);
    }

    private String getDefaultMessage(){
        return MessageFormat.format("用户{0}不存在", super.getUsername());
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if(message==null){
            message = getDefaultMessage();
        }
        return message;
    }
}
