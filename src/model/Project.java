import java.io.Serializable;
import java.util.*;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Map<Integer, Issue> issues = new HashMap<>();
    private List<User> members = new ArrayList<>();

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addIssue(Issue issue) { issues.put(issue.getId(), issue); }
    public void removeIssue(int id) { issues.remove(id); }
    public Issue getIssue(int id) { return issues.get(id); }
    public Collection<Issue> getIssues() { return issues.values(); }
    public void addMember(User user) { members.add(user); }
    public List<User> getMembers() { return members; }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() { return name; }
}