import java.io.Serializable;

public class Bug extends Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    private String severity;
    private String stepsToReproduce;

    public Bug(int id, String title, String description, Priority priority, int projectId,
               String severity, String steps) {
        super(id, title, description, priority, projectId);
        this.severity = severity;
        this.stepsToReproduce = steps;
    }

    @Override
    public String getType() { return "BUG"; }

    public String getSeverity() { return severity; }
    public void setSeverity(String s) { this.severity = s; }
    public String getStepsToReproduce() { return stepsToReproduce; }
    public void setStepsToReproduce(String s) { this.stepsToReproduce = s; }
}