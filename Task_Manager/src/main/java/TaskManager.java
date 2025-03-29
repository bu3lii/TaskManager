import java.util.*;

public class TaskManager {
    private List<Task> taskList = new ArrayList<>();

    public void addTask (Task task){
        taskList.add(task);
    }

    public void listTasks(){
        System.out.println(taskList);
    }

    public List<Task> returnList(){
        return taskList;
    }

    public void markTastComplete(int index){
        if (index>=0 && index < taskList.size()){
            taskList.get(index).markasComplete();
        }
    }

    public void deleteTask(int index){
        if (index>=0 && index < taskList.size()) {
            taskList.remove(index);
        }
    }
    }
