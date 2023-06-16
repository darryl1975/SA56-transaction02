package sg.edu.nus.iss.springdemo.exception;

public class ErrorInfo {
    private final String url;
    private final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    public ErrorInfo(String url, String errorMessage) {
        this.url = url;
        this.ex = errorMessage;
    }
}
