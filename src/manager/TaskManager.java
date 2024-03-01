package manager;

import status.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Subtask> subtasks;
    private final HashMap<Integer, Epic> epics;

    public TaskManager() {
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
        epics.clear();
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
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        for(Map.Entry<Integer, Task> task : tasks.entrySet()) {
            taskArrayList.add(task.getValue());
        }

        return taskArrayList;
    }

    /** Удаление всех задач */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /** Получение по идентификатору */
    public Task getTaskById(int id) {
        if(tasks.containsKey(id))
            return tasks.get(id);
        return null;
    }

    /** Создание. + Обновление. Сам объект должен передаваться в качестве параметра. Новая версия объекта с верным идентификатором передаётся в виде параметра */
    public void setTask(Task task) {
        tasks.put(task.getId(), task);
    }

    /** Удаление по идентификатору */
    public void deleteTaskById(int id) {
        if(tasks.containsKey(id))
            tasks.remove(id);
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
        subtasks.clear();
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
