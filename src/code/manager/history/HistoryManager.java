package code.manager.history;

import code.tasks.Task;

import java.util.ArrayList;

/** Интерфейс для управления историей просмотров */
public interface HistoryManager {
    /** Для добавления задачи в менеджер историй.
     * @param task - задача, которую требуется добавить.
     * */
    void add(Task task);

    /** Для получения истории последних задач.
     * @return список задач.
     * */
    ArrayList<Task> getHistory();
}
