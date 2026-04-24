import java.io.*;

public class DataStorage {
    private static final String FILE_PATH = "data/storage.dat";

    public void save(AppData data) {
        new File("data").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    public AppData load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Новое хранилище создано");
            return new AppData();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            return (AppData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки: " + e.getMessage());
            return new AppData();
        }
    }
}