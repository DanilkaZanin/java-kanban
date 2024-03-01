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

    /** Добавление задачи */
    public void setEpic(Epic epic){
        idCounter ++;

        if(epic.getStatus() == null){
            if(epic.getSubtasks().isEmpty())
                epic = new Epic(epic, Status.NEW);
            else{
                int countOfNewTasks = 0;
                int countOfDoneTasks = 0;

                for (Subtask subtask : epic.getSubtasks()){
                    subtasks.put(subtask.getId(), subtask);

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

    /** Получение списка всех задач */
    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    //Для тасков

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTask(Task task) {
        if(!tasks.containsKey(task.getId())){
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
        if(!subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.hashCode(), subtask);
        }
    }

    /** Обновление подзадачи, если она существует */
    public void updateSubtask(Subtask subtask) {
        if(subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(),subtask);
        }
    }

    /** Удаление по идентификатору */
    public void deleteSubtaskById(int id) {
        if(subtasks.containsKey(id))
            subtasks.remove(id);
    }


    /** Удаление всех задач */

    /** Получение по идентификатору */

    /** Создание. Сам объект должен передаваться в качестве параметра. */
/*
    a. Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.
    b. Для эпиков:
    если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
    если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
    во всех остальных случаях статус должен быть IN_PROGRESS.
*/


    /** Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра */

    /** Удаление по идентификатору */

    /** Получение списка всех подзадач определённого эпика */
}
