package code.manager.history;

import code.tasks.Task;

import java.util.List;

/** Интерфейс для управления историей просмотров */
public interface HistoryManager {
    /** Для добавления задачи в менеджер историй.
     * @param task - задача, которую требуется добавить.
     * */
    <T extends Task> void add(T task) ;

    /** Для получения истории последних задач.
     * @return список задач.
     * */
    List<Task> getHistory();
}
