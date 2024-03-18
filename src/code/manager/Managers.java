package code.manager;

import code.manager.history.HistoryManager;
import code.manager.history.InMemoryHistoryManager;
import code.manager.task.InMemoryTaskManager;
import code.manager.task.TaskManager;

// непонятно зачем нужно
public final class Managers {
    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
