package code.manager.task;

import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;

import java.util.List;

public interface TaskManager {
    List<Epic> getEpics();

    void deleteEpics();

    List<Subtask> getSubtasksFromEpic(Epic epic);

    Epic getEpic(int id);

    void setEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpic(int id);

    List<Task> getTasks();

    void deleteTasks();

    Task getTask(int id);

    void setTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    List<Subtask> getSubtasks();

    void deleteSubtasks();

    Subtask getSubtask(int id);

    void setSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void deleteSubtask(int id);

    List<Task> getHistory();
}
