public class UserService {
    private AppData appData;
    private DataStorage storage;

    public UserService(AppData appData, DataStorage storage) {
        this.appData = appData;
        this.storage = storage;
    }

    public Response login(String payload) {
        String[] parts = payload.split(":");
        String username = parts[0];
        String password = parts[1];

        for (User u : appData.getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return Response.ok(u.toString());
            }
        }
        return Response.error("Неверный логин или пароль");
    }

    public Response register(String payload) {
        String[] parts = payload.split(":");
        String username = parts[0];
        String password = parts[1];

        for (User u : appData.getUsers()) {
            if (u.getUsername().equals(username)) {
                return Response.error("Пользователь уже существует");
            }
        }

        User newUser = new User(appData.getNextUserId(), username, password, Role.DEVELOPER);
        appData.getUsers().add(newUser);
        storage.save(appData);
        return Response.ok("Зарегистрирован: " + username);
    }
}