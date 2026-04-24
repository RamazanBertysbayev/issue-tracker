import java.net.*;
import java.io.*;

public class ServerConnection {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void connect() throws IOException {
        socket = new Socket(HOST, PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Подключено к серверу");
    }

    public Response send(Request request) throws IOException, ClassNotFoundException {
        out.writeObject(request);
        out.flush();
        return (Response) in.readObject();
    }

    public void disconnect() {
        try { socket.close(); } catch (IOException e) { }
    }
}