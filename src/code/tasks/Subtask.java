package code.tasks;

import code.status.Status;

public final class Subtask extends Task {
    /** В тз указано, что каждая подзадача должна знать, в каком эпике она находится */
    private final int epic;

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, int epic){
        super(name, description, Status.NEW);
        this.epic = 0;
    }

    /** Для создания подзадачи в эпике */
    public Subtask(String name, String description, Status status, int epic) {
        super(name, description, status);
        this.epic = epic;
    }

    /** Возвращает эпик, внутри которого находится */
    public int getEpic(){
        return epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                ",name='" + getName() + '\'' +
                ",description='" + getDescription() + '\'' +
                ",id='" + getId() + '\'' +
                ",status='" + getStatus() + '\''+
                ",epicID='" + epic + '\'' +
                '}';
    }
}
