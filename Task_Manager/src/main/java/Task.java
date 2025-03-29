public class Task {
    private String name,description,duedate;
    private boolean complete;

    public Task (String name, String description, String duedate, boolean complete){
        this.name = name;
        this.description = description;
        this.duedate = duedate;
        this.complete = complete;
    }

    public void markasComplete(){
        complete=true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuedate() {
        return duedate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task:" +
                "Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                ", Duedate='" + duedate + '\'' +
                ", Complete=" + complete +
                '}';
    }
}
