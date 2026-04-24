import java.io.Serializable;
import java.time.LocalDate;

public class Task extends Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate deadline;
    private int estimateHours;

    public Task(int id, String title, String description, Priority priority, int projectId,
                LocalDate deadline, int estimateHours) {
        super(id, title, description, priority, projectId);
        this.deadline = deadline;
        this.estimateHours = estimateHours;
    }

    @Override
    public String getType() { return "TASK"; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate d) { this.deadline = d; }
    public int getEstimateHours() { return estimateHours; }
    public void setEstimateHours(int h) { this.estimateHours = h; }
}