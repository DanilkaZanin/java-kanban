package manager;

import status.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    /** Поле для генерации идентификаторов */
    private int idCounter;

    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;

    public TaskManager() {
        this.idCounter = 0;
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
        epics = new HashMap<>();
    }

    // Для эпиков

    /** Получение списка всех эпиков */
    public ArrayList<Epic> getListOfAllEpics() {
        ArrayList<Epic> epicArrayList = new ArrayList<>();

        for(Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            epicArrayList.add(entry.getValue());
        }

        return epicArrayList;
    }

    /** Удаление всех эпиков */
    public void deleteAllEpics() {
        for(Map.Entry<Integer, Epic> epic : epics.entrySet()){
            for (Subtask subtask : epic.getValue().getSubtasks()){
                deleteSubtaskById(subtask.getId());
            }
        }
        epics = new HashMap<>();
    }

    /** Получение списка всех подзадач определённого эпика */
    public ArrayList<Subtask> getAllSubtasksFromEpic(Epic epic){
        return epic.getSubtasks();
    }

    /** Получение по идентификатору */
    public Epic getEpic(int id) {
        if(epics.containsKey(id))
            return epics.get(id);
        return null;
    }

    /** Создание эпика */
    public void setEpic(Epic epic) {
        idCounter ++;

        if(epic.getStatus() == null) {
            if(epic.getSubtasks().isEmpty())
                epic = new Epic(epic, Status.NEW);
            else {
                int countOfNewTasks = 0;
                int countOfDoneTasks = 0;

                for (Subtask subtask : epic.getSubtasks()) {
                    setSubtask(subtask);

                    if(subtask.getStatus().equals(Status.NEW))
                        countOfNewTasks ++;
                    else if(subtask.getStatus().equals(Status.DONE))
                        countOfDoneTasks ++;
                }

                if(countOfNewTasks == epic.getSubtasks().size())
                    epic = new Epic(epic, Status.NEW);
                else if(countOfDoneTasks == epic.getSubtasks().size())
                    epic = new Epic(epic, Status.DONE);
                else
                    epic = new Epic(epic, Status.IN_PROGRESS);
            }
        }
        epics.put(epic.hashCode(),epic);
    }

    //Для задач

    /** Получение списка всех задач*/
    public ArrayList<Task> deleteAllTasks(){
        return null;
    }
    /** Удаление списка всех задач */

    /** Получение по идентификатору */

    /** Создание. + Обновление. Сам объект должен передаваться в качестве параметра. Новая версия объекта с верным идентификатором передаётся в виде параметра */

    /** Удаление по идентификатору */

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTask(Task task) {
        if(!tasks.containsKey(task.getId())) {
            tasks.put(task.hashCode(), task);
        }
    }

    //Для подзадач

    /** Получение списка всех подзадач */
    public ArrayList<Subtask> getListOfAllSubtasks() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();

        for(Map.Entry<Integer,Subtask> entry : subtasks.entrySet()) {
            subtaskArrayList.add(entry.getValue());
        }

        return subtaskArrayList;
    }

    /** Удаление всех подзадач */
    public void deleteAllSubtasks() {
        subtasks = new HashMap<>();
    }

    /** Получение подзадачи по идентификатору*/
    public Subtask getSubtaskById(int id) {
        if (subtasks.containsKey(id))
            return subtasks.get(id);
        return null;
    }

    /** Добавление подзадачи */
    public void setSubtask(Subtask subtask) {
        subtasks.put(subtask.hashCode(), subtask);
    }

    /** Удаление по идентификатору */
    public void deleteSubtaskById(int id) {
        if(subtasks.containsKey(id))
            subtasks.remove(id);
    }
}
