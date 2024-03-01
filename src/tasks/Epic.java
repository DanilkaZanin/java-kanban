package tasks;

import status.Status;
import java.util.ArrayList;

/*
Для каждой подзадачи известно, в рамках какого эпика она выполняется.
Каждый эпик знает, какие подзадачи в него входят.
Завершение всех подзадач эпика считается завершением эпика.*/
public class Epic extends Task {
    ArrayList<Subtask> subtasks;
    public Epic(String name, String description, Status status) {
        super(name, description, status);
        subtasks = new ArrayList<>();
    }

    public Epic(String name, String description, Status status, ArrayList<Subtask> subtasks) {
        super(name, description, status);
        this.subtasks = subtasks;
    }

    public Epic(String name, String description, Status status, Subtask subtask) {
        super(name, description, status);

        subtasks = new ArrayList<>();
        this.subtasks.add(subtask);
    }

    public Epic(String name, String description) {
        super(name, description, null);
        subtasks = new ArrayList<>();
    }

    public Epic(String name, String description, ArrayList<Subtask> subtasks) {
        super(name, description, null);
        this.subtasks = subtasks;
    }

    public Epic(String name, String description, Subtask subtask) {
        super(name, description, null);

        subtasks = new ArrayList<>();
        this.subtasks.add(subtask);
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
