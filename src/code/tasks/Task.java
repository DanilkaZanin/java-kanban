package code.tasks;

import code.status.Status;

import java.util.Objects;

public class Task {
    protected final String name;
    protected final String description;
    protected int id;
    protected Status status;

    /** Конструктор для создания задачи */
    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        id = 0;
    }

    /** Конструктор копирования */
    public Task(Task task) {
        this.name = task.name;
        this.description = task.description;
        this.status = task.status;
        id = task.getId();
    }


    /** Возвращает имя */
    public String getName() {
        return name;
    }

    /** Возвращает описание */
    public String getDescription() {
        return description;
    }

    /** Возвращает id */
    public int getId() {
        return id;
    }

    /** Возвращает статус задачи */
    public Status getStatus() {
        return status;
    }

    /** Добавление статуса */
    public void setStatus(Status status) {
        this.status = status;
    }

    /** Добавление id
     * Нежелателен для вызова вне TaskManager */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                status == task.status;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ",description='" + description + '\'' +
                ",id='" + id + '\'' +
                ",status='" + status + '\'' +
                "}";
    }
}
