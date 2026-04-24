import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 8080;
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private IssueService issueService;
    private UserService userService;

    public Server() {
        DataStorage storage = new DataStorage();
        AppData appData = storage.load();

        if (appData.getProjects().isEmpty()) {
            Project defaultProject = new Project(appData.getNextProjectId(), "Default Project");
            appData.getProjects().add(defaultProject);
            storage.save(appData);
            System.out.println("Создан дефолтный проект");
        }

        issueService = new IssueService(appData, storage);
        userService = new UserService(appData, storage);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + clientSocket.getInetAddress());
                threadPool.execute(new ClientHandler(clientSocket, issueService, userService));
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}