import java.io.Serializable;

public class Story extends Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    private int storyPoints;
    private String epicName;

    public Story(int id, String title, String description, Priority priority, int projectId,
                 int storyPoints, String epicName) {
        super(id, title, description, priority, projectId);
        this.storyPoints = storyPoints;
        this.epicName = epicName;
    }

    @Override
    public String getType() { return "STORY"; }

    public int getStoryPoints() { return storyPoints; }
    public void setStoryPoints(int p) { this.storyPoints = p; }
    public String getEpicName() { return epicName; }
    public void setEpicName(String e) { this.epicName = e; }
}