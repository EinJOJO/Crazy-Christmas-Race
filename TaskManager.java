import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    
    List<Task> tasks = new ArrayList<>();

    public TaskManager() {

    }

    public void runNextTask() {
        if (tasks.isEmpty()) return;
        Task task = tasks.get(0);
        
        try {
            task.execute();
        } catch (Exception e) {
            System.out.println("Exception while running a task");
            e.printStackTrace();
        }

    }

    interface Task {
        void execute();
    }



    
}
