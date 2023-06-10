package domain;

public enum StatusCode {
    BAD_REQUEST(400, "Bad Request", "CODE 400, BAD REQUEST"),
    NOT_FOUND(404, "Not Found", "CODE 404, NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "CODE 405, METHOD NOT ALLOWED"),
    OK(200, "OK", "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "CODE 500, INTERNAL SERVER ERROR");


    private final int code;
    private final String statusMessage;
    private final String displayMessage;

    StatusCode(int code, String statusMessage, String displayMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public String getDisplayMessage() {
        return displayMessage;
    }
}
