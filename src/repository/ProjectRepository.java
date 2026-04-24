import java.util.*;

public class ProjectRepository implements IRepository<Project> {
    private Map<Integer, Project> store = new HashMap<>();

    @Override
    public synchronized void add(Project project) { store.put(project.getId(), project); }

    @Override
    public synchronized Project getById(int id) { return store.get(id); }

    @Override
    public synchronized List<Project> getAll() { return new ArrayList<>(store.values()); }

    @Override
    public synchronized void update(Project project) { store.put(project.getId(), project); }

    @Override
    public synchronized void delete(int id) { store.remove(id); }
}