import java.io.Serializable;
import java.util.*;

public class AppData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Project> projects = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private int nextIssueId = 1;
    private int nextUserId = 1;
    private int nextCommentId = 1;
    private int nextProjectId = 1;

    public synchronized int getNextIssueId() { return nextIssueId++; }
    public synchronized int getNextUserId() { return nextUserId++; }
    public synchronized int getNextCommentId() { return nextCommentId++; }
    public synchronized int getNextProjectId() { return nextProjectId++; }

    public List<Project> getProjects() { return projects; }
    public List<User> getUsers() { return users; }
    public List<Comment> getComments() { return comments; }
}