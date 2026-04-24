import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private IssueService issueService;
    private UserService userService;

    public ClientHandler(Socket socket, IssueService is, UserService us) {
        this.socket = socket;
        this.issueService = is;
        this.userService = us;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            while (true) {
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                out.writeObject(response);
                out.flush();
            }
        } catch (EOFException e) {
            System.out.println("Клиент отключился");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private Response handleRequest(Request req) {
        switch (req.getAction()) {
            case "CREATE_ISSUE":   return issueService.create(req.getPayload());
            case "GET_ALL_ISSUES": return issueService.getAll();
            case "UPDATE_ISSUE":   return issueService.update(req.getPayload());
            case "DELETE_ISSUE":   return issueService.delete(req.getPayload());
            case "LOGIN":          return userService.login(req.getPayload());
            case "REGISTER":       return userService.register(req.getPayload());
            default:               return Response.error("Неизвестное действие");
        }
    }
}