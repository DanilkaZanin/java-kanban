package code.manager.history;

import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> taskList;

    public InMemoryHistoryManager() {
        taskList = new ArrayList<>(10);
    }

    public <T extends Task> void add(T task) {
        if(taskList.size() > 10) {
            taskList.remove(0);
        }

        if(task instanceof Subtask)
            taskList.add(new Subtask((Subtask) task));
        else if(task instanceof Epic)
            taskList.add(new Epic((Epic) task));
        else
            taskList.add(new Task(task));
    }

    @Override
    public List<Task> getHistory() {
        return taskList;
    }
}
