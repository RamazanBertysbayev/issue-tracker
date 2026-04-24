import java.util.*;

public class IssueService {
    private AppData appData;
    private DataStorage storage;

    public IssueService(AppData appData, DataStorage storage) {
        this.appData = appData;
        this.storage = storage;
    }

    public Response getAll() {
        StringBuilder sb = new StringBuilder();
        for (Project p : appData.getProjects()) {
            for (Issue issue : p.getIssues()) {
                sb.append(issue.getId()).append("|")
                  .append(issue.getType()).append("|")
                  .append(issue.getTitle()).append("|")
                  .append(issue.getStatus()).append("|")
                  .append(issue.getPriority()).append("\n");
            }
        }
        return Response.ok(sb.toString());
    }

    public Response create(String payload) {
        String[] parts = payload.split("\\|");
        String type = parts[0];
        String title = parts[1];
        String description = parts[2];
        Priority priority = Priority.valueOf(parts[3]);
        int projectId = Integer.parseInt(parts[4]);

        Issue issue;
        int id = appData.getNextIssueId();

        if (type.equals("BUG")) {
            issue = new Bug(id, title, description, priority, projectId, "MEDIUM", "");
        } else if (type.equals("TASK")) {
            issue = new Task(id, title, description, priority, projectId, null, 0);
        } else {
            issue = new Story(id, title, description, priority, projectId, 0, "");
        }

        for (Project p : appData.getProjects()) {
            if (p.getId() == projectId) {
                p.addIssue(issue);
                break;
            }
        }

        storage.save(appData);
        return Response.ok(issue.toString());
    }

    public Response delete(String payload) {
        int id = Integer.parseInt(payload);
        for (Project p : appData.getProjects()) {
            p.removeIssue(id);
        }
        storage.save(appData);
        return Response.ok("Удалено");
    }

    public Response update(String payload) {
        String[] parts = payload.split("\\|");
        int id = Integer.parseInt(parts[0]);
        Status newStatus = Status.valueOf(parts[1]);

        for (Project p : appData.getProjects()) {
            Issue issue = p.getIssue(id);
            if (issue != null) {
                issue.setStatus(newStatus);
                storage.save(appData);
                return Response.ok(issue.toString());
            }
        }
        return Response.error("Issue не найден");
    }
}