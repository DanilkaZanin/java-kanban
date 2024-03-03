package code.tasks;

import code.status.Status;

public final class Subtask extends Task {
    /** В тз указано, что каждая подзадача должна знать, в каком эпике она находится */
    private final Epic epic;

    /** Для создания подзадачи без эпика */
    public Subtask(String name, String description){
        super(name, description, Status.NEW);
        epic = null;
    }

    /** Для создания подзадачи без эпика */
    public Subtask(String name, String description, Status status) {
        super(name, description, status);
        epic = null;
    }

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, Epic epic){
        super(name, description, Status.NEW);
        this.epic = epic;
    }

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

    /** Возвращает эпик, внутри которого находится */
    public Epic getEpic(){
        return epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                ",name='" + getName() + '\'' +
                ",description='" + getDescription() + '\'' +
                ",id='" + getId() + '\'' +
                ",status='" + getStatus() + '\''+
                ",epic='" + epic.getName() + '\'' +
                '}';
    }
}
