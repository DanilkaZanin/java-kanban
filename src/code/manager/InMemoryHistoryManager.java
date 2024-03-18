package code.manager;

import code.tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager{
    private final ArrayList<Task> taskList;

    public InMemoryHistoryManager() {
        taskList = new ArrayList<>(10);

    }

    @Override
    public void add(Task task) {
        if(taskList.size() > 10) {
            taskList.remove(0);
        }

        taskList.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return taskList;
    }
}
