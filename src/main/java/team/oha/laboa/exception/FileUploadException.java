package team.oha.laboa.exception;

import java.text.MessageFormat;

public class FileUploadException extends FileException {
    private String fileName;

    public FileUploadException(String fileName) {
        this.fileName = fileName;
    }

    public FileUploadException(String message, String fileName) {
        super(message);
        this.fileName = fileName;
    }

    public FileUploadException(String message, Throwable cause, String fileName) {
        super(message, cause);
        this.fileName = fileName;
    }

    public FileUploadException(Throwable cause, String fileName) {
        super(cause);
        this.fileName = fileName;
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String fileName) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.fileName = fileName;
    }

    private String getDefaultMessage(){
        return MessageFormat.format("文件{0}上传失败", fileName);
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
