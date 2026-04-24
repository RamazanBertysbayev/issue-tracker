import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Issue implements Serializable, Comparable<Issue> {
    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private User assignee;
    private LocalDateTime createdAt;
    private int projectId;

    public Issue(int id, String title, String description, Priority priority, int projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.NEW;
        this.createdAt = LocalDateTime.now();
        this.projectId = projectId;
    }

    public abstract String getType();

    @Override
    public int compareTo(Issue other) {
        return Integer.compare(
            other.priority.ordinal(),
            this.priority.ordinal()
        );
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority p) { this.priority = p; }
    public User getAssignee() { return assignee; }
    public void setAssignee(User u) { this.assignee = u; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getProjectId() { return projectId; }

    @Override
    public String toString() {
        return "[" + getType() + " #" + id + "] " + title + " | " + status + " | " + priority;
    }
}