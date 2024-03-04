package code.manager;

import code.status.Status;
import code.tasks.Subtask;
import code.tasks.Task;
import code.tasks.Epic;

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

    /** Получение списка всех эпиков
     * @return Список всех эпиков
     * */
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    /** Удаление всех эпиков */
    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    /** Получение списка всех подзадач определённого эпика
     * @param epic - Эпик, из которого требуется получить список подзадач
     * @return Список всех подзадач из эпика
     * */
    public ArrayList<Subtask> getSubtasksFromEpic(Epic epic){
        ArrayList<Subtask> subtasksList = new ArrayList<>();

        for (int id : epic.getSubtasksID()){
            subtasksList.add(subtasks.get(id));
        }

        return subtasksList;
    }

    /** Получение по идентификатору */
    public Epic getEpic(int id) {
        if(epics.containsKey(id))
            return epics.get(id);
        return null;
    }

    /** Создание и обновления эпика */
    public void setEpic(Epic epic) {
        if (epic.getSubtasksID().isEmpty())
            epic.setStatus(Status.NEW);
        else {
            int countOfNewTasks = 0;
            boolean isInProgressTask = false;

            for (int id : epic.getSubtasksID()) {
                if(subtasks.get(id).getStatus().equals(Status.IN_PROGRESS)){
                    isInProgressTask = true;
                    break;
                } else if (subtasks.get(id).getStatus().equals(Status.NEW))
                    countOfNewTasks++;
            }
            if(isInProgressTask)
                epic.setStatus(Status.IN_PROGRESS);
            else if (countOfNewTasks == epic.getSubtasksID().size())
                epic.setStatus(Status.NEW);
            else
                epic.setStatus(Status.NEW);
        }
        epics.put(epic.hashCode(), epic);
    }

    /** Удаление эпика по id*/
    public void deleteEpic(int id) {
        if(epics.containsKey(id)) {
            ArrayList<Integer> subtaskIDArrayList = epics.get(id).getSubtasksID();

            for(int subtaskId : subtaskIDArrayList){
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        }
    }

    /** Получение списка всех задач
     * @return Список всех задач
     * */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    /** Удаление всех задач */
    public void deleteTasks() {
        tasks.clear();
    }

    /** Получение по идентификатору
     * @param id - идентификатор требуемой задачи
     * @return требуемая задача*/
    public Task getTask(int id) {
        if(tasks.containsKey(id))
            return tasks.get(id);
        return null;
    }

    /** Создание. + Обновление.
     * Новая версия объекта с верным идентификатором передаётся в виде параметра
     * @param task - объект, который требуется добавить или обновить
     * */
    public void setTask(Task task) {
        tasks.put(task.getId(), task);
    }

    /** Удаление по идентификатору
     * @param id - идентификатор задачи
     * */
    public void deleteTask(int id) {
        if(tasks.containsKey(id))
            tasks.remove(id);
    }

    /** Получение списка всех подзадач
     * @return  Список всех подзадач
     * */
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    /** Удаление всех подзадач */
    public void deleteSubtasks() {
        ArrayList<Integer> keys = new ArrayList<>();

        for (Map.Entry<Integer, Subtask> subtask : subtasks.entrySet()) {
            keys.add(subtask.getKey());
        }

        for (int key : keys) {
            deleteSubtask(key);
        }
    }

    /** Получение подзадачи по идентификатору
     * @param id - идентификатор подзадачи
     * */
    public Subtask getSubtask(int id) {
        if (subtasks.containsKey(id))
            return subtasks.get(id);
        return null;
    }

    /** Добавление/Обновление подзадачи */
    public void setSubtask(Subtask subtask) {
        subtasks.put(subtask.hashCode(), subtask);
    }

    /** Удаление по идентификатору
     * @param id - идентификатор подзадачи
     * */
    public void deleteSubtask(int id) {
        if(subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpic());

            epic.getSubtasksID().remove(id);
            subtasks.remove(id);
        }
    }
}
