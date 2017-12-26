package zw.co.sbs.steward.freelancer.domain;

public class ErrorManager {
    private String responseCode;
    private String description;
    private String message;


    public ErrorManager() {

    }

    public ErrorManager(String description) {

        this.description = description;
    }
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
