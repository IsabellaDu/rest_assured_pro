package arguments.holders;

import java.util.Map;

public class LabelIdValidationArgumentsHolder {

    private final Map<String, String> pathParams;

    private final String errorMessage;

    private final int statusCode;

    public LabelIdValidationArgumentsHolder(Map<String, String> pathParams, String errorMessage, int statusCode) {
        this.pathParams = pathParams;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
