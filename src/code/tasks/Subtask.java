package code.tasks;

import code.status.Status;

public final class Subtask extends Task {
    /** Идентификатор эпика, внутри которого находится подзадача */
    private final int epicId;

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, int epicId){
        super(name, description, Status.NEW);
        this.epicId = epicId;
    }

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, Status status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    /** Возвращает идентификатор эпика, внутри которого находится */
    public int getEpicId(){
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                ",name='" + getName() + '\'' +
                ",description='" + getDescription() + '\'' +
                ",id='" + getId() + '\'' +
                ",status='" + getStatus() + '\''+
                ",epicId='" + epicId + '\'' +
                '}';
    }
}
