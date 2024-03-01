package tasks;

import status.Status;

public final class Subtask extends Task {
    /** В тз указано, что каждая подзадача должна знать, в каком эпике она находится */
    private final Epic epic;
    public Subtask(String name, String description, Status status) {
        super(name, description, status);
        epic = null;
    }

    /** Конструктор*/
    public Subtask(String name, String description){
        super(name, description, Status.NEW);
        epic = null;
    }

    public Subtask(String name, String description, Status status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

    public Subtask(String name, String description, Epic epic){
        super(name, description, Status.NEW);
        this.epic = epic;
    }

    public Epic getEpic(){
        return epic;
    }

}
