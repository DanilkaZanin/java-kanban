package tasks;

import status.Status;
import java.util.Objects;

public class Task {
    private final String name;
    private final String description;
    private final int id;
    private final Status status;

    /** Конструктор для создания задачи */
    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
        id = hashCode();
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
        return Objects.hash(name, description);
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
