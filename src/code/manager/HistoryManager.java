package code.manager;

import code.tasks.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void add(Task task);

    /** Для получения истории последних 10 задач*/
    ArrayList<Task> getHistory();
}
