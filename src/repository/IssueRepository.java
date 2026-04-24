import java.util.*;

public class IssueRepository implements IRepository<Issue> {
    private Map<Integer, Issue> store = new HashMap<>();

    @Override
    public synchronized void add(Issue issue) {
        store.put(issue.getId(), issue);
    }

    @Override
    public synchronized Issue getById(int id) {
        return store.get(id);
    }

    @Override
    public synchronized List<Issue> getAll() {
        List<Issue> list = new ArrayList<>(store.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public synchronized void update(Issue issue) {
        store.put(issue.getId(), issue);
    }

    @Override
    public synchronized void delete(int id) {
        store.remove(id);
    }

    public synchronized List<Issue> getByStatus(Status status) {
        List<Issue> result = new ArrayList<>();
        for (Issue i : store.values()) {
            if (i.getStatus() == status) result.add(i);
        }
        return result;
    }

    public synchronized List<Issue> getByProject(int projectId) {
        List<Issue> result = new ArrayList<>();
        for (Issue i : store.values()) {
            if (i.getProjectId() == projectId) result.add(i);
        }
        return result;
    }

    public synchronized List<Issue> getPaged(int offset, int limit) {
        return store.values().stream()
            .skip(offset)
            .limit(limit)
            .collect(java.util.stream.Collectors.toList());
    }
}