public class ListItem {
    String task, deadline;
    int points, importance;

    public ListItem (String task, String deadline, int points, int importance) {
        this.task = task;
        this.deadline = deadline;
        this.points = points;
        this.importance = importance;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
