package org.franciscobravo.repaso6note.exception;

public class ApiError {
    private int status;
    private String error;
    private String message;
    private String path;


    public ApiError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;

    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status;
    }


}
