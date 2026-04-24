import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String data;
    private String message;

    public Response(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static Response ok(String data) {
        return new Response("OK", data, null);
    }

    public static Response error(String message) {
        return new Response("ERROR", null, message);
    }

    public String getStatus() { return status; }
    public String getData() { return data; }
    public String getMessage() { return message; }
}