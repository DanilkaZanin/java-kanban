package code.manager;

import code.status.Status;
import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;

import java.util.ArrayList;

public interface TaskManager {
    ArrayList<Epic> getEpics();

    void deleteEpics();

    ArrayList<Subtask> getSubtasksFromEpic(Epic epic);

    Epic getEpic(int id);

    void setEpic(Epic epic);

    /**
     * Метод обновления эпика
     */
    void updateEpic(Epic epic);

    void deleteEpic(int id);

    ArrayList<Task> getTasks();

    void deleteTasks();

    Task getTask(int id);

    void setTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    ArrayList<Subtask> getSubtasks();

    void deleteSubtasks();

    Subtask getSubtask(int id);

    void setSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void deleteSubtask(int id);

    ArrayList<Task> getHistory();
}
