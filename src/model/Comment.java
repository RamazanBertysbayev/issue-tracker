import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int issueId;
    private User author;
    private String text;
    private LocalDateTime createdAt;

    public Comment(int id, int issueId, User author, String text) {
        this.id = id;
        this.issueId = issueId;
        this.author = author;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public int getIssueId() { return issueId; }
    public User getAuthor() { return author; }
    public String getText() { return text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}