package tasks;

import status.Status;
import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasks;

    /** Для создания пустого эпика */
    public Epic(String name, String description) {
        super(name, description, null);
        subtasks = new ArrayList<>();
    }

    /** Конструктор для изменения статуса */
    public Epic(Epic epic, Status status){
        super(epic.getName(), epic.getDescription(), status);
    }

    /** Добавление подзадачи в задачу */
    public void setSubtask(Subtask subtask){
        subtasks.add(subtask);
    }

    /** Добавление одной подзадачи */
    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    /** Получение списка подзадач */
    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}
