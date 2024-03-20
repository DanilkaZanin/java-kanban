package code.tasks;

import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> subtasksId;

    /** Для создания пустого эпика */
    public Epic(String name, String description) {
        super(name, description, null);
        subtasksId = new ArrayList<>();
    }

    /**Для копирования Эпика */
    public Epic(Epic epic) {
        super(epic);
        subtasksId = epic.subtasksId;
    }

    /** Добавление подзадачи в эпик
     * @param id - идентификатор подзадачи */
    public int setSubtaskId(int id){
        if(id != this.id){
            subtasksId.add(id);
            return 0;
        }
        return -1;
    }

    /** Очистка списка идентификаторов подзадач */
    public void clearSubtasksId(){
        subtasksId.clear();
    }

    /** Получение списка идентификаторов подзадач */
    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + getName() + '\'' +
                ",description='" + getDescription() + '\'' +
                ",id='" + getId() + '\'' +
                ",status='" + getStatus() + '\'' +
                ",subtasksId=" + subtasksId +
                "}";
    }
}
