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
public class WrongPasswordException extends UserException {
    private String password;

    public String getPassword() {
        return password;
    }

    public WrongPasswordException(String username, String password) {
        super(username);
        this.password = password;
    }

    public WrongPasswordException(String message, String username, String password) {
        super(message, username);
        this.password = password;
    }

    public WrongPasswordException(String message, Throwable cause, String username, String password) {
        super(message, cause, username);
        this.password = password;
    }

    public WrongPasswordException(Throwable cause, String username, String password) {
        super(cause, username);
        this.password = password;
    }

    public WrongPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String username, String password) {
        super(message, cause, enableSuppression, writableStackTrace, username);
        this.password = password;
    }

    private String getDefaultMessage(){
        return MessageFormat.format("用户{0}的密码{1}不正确", super.getUsername(), getPassword());
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
