package code.manager;

import code.manager.history.HistoryManager;
import code.manager.history.InMemoryHistoryManager;
import code.manager.task.InMemoryTaskManager;
import code.manager.task.TaskManager;

public final class Managers {
    /** Возвращает экземпляр класса, который реализует интерфейс TaskManager */
    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    /** Возвращает экземпляр класса, который реализует интерфейс HistoryManager */
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
