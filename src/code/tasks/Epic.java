package code.tasks;

import code.status.Status;

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
        subtasks = epic.getSubtasks();
    }

    /** Добавление подзадачи в эпик */
    public void setSubtask(Subtask subtask){
        subtasks.add(subtask);
    }

    /** Получение списка подзадач */
    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ",description='" + getDescription() + '\'' +
                ",id='" + getId() + '\'' +
                ",status='" + getStatus() + '\'' +
                ",subtasks=" + subtasks +
                "}";
    }
}
