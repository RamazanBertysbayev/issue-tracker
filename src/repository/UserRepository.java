import java.util.*;

public class UserRepository implements IRepository<User> {
    private Map<Integer, User> store = new HashMap<>();

    @Override
    public synchronized void add(User user) { store.put(user.getId(), user); }

    @Override
    public synchronized User getById(int id) { return store.get(id); }

    @Override
    public synchronized List<User> getAll() { return new ArrayList<>(store.values()); }

    @Override
    public synchronized void update(User user) { store.put(user.getId(), user); }

    @Override
    public synchronized void delete(int id) { store.remove(id); }

    public synchronized User findByUsername(String username) {
        for (User u : store.values()) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}