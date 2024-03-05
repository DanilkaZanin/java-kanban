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
    private int counter;

    public TaskManager() {
        tasks = new HashMap<>();
        subtasks = new HashMap<>();
        epics = new HashMap<>();
        counter = Integer.MIN_VALUE;
    }

    private int generateId(){
        return counter++;
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

        for (int id : epic.getSubtasksId()){
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

    /** Генератор статуса в эпике  */
    private void epicStatusGenerator(Epic epic){
        int countOfNewTasks = 0;
        boolean isInProgressTask = false;

        for (int id : epic.getSubtasksId()) {
            if(subtasks.get(id).getStatus().equals(Status.IN_PROGRESS)){
                isInProgressTask = true;
                break;
            } else if (subtasks.get(id).getStatus().equals(Status.NEW))
                countOfNewTasks++;
        }
        if(isInProgressTask)
            epic.setStatus(Status.IN_PROGRESS);
        else if (countOfNewTasks == epic.getSubtasksId().size())
            epic.setStatus(Status.NEW);
        else
            epic.setStatus(Status.DONE);
    }

    /** Создание эпика */
    public void setEpic(Epic epic) {
        epic.setStatus(Status.NEW);
        epic.setId(generateId());

        epics.put(epic.getId(), epic);
    }

    /** Метод обновления эпика */
    private void updateEpic(Epic epic){
        if(!epic.getSubtasksId().isEmpty()){
            epicStatusGenerator(epic);
        } else
            epic.setStatus(Status.NEW);

        epics.put(epic.getId(),epic);
    }

    /** Удаление эпика по id*/
    public void deleteEpic(int id) {
        if(epics.containsKey(id)) {
            ArrayList<Integer> subtaskIDArrayList = epics.get(id).getSubtasksId();

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

    /** Создание
     * Новая версия объекта с верным идентификатором передаётся в виде параметра
     * @param task - объект, который требуется добавить
     * */
    public void setTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    /** Обновление
     * @param task - объект, который требуется обновить */
    public void updateTask(Task task){
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
        subtasks.clear();

        for(Map.Entry<Integer,Epic> entry : epics.entrySet()){
            if(entry.getValue().getSubtasksId()!= null){
                entry.getValue().clearSubtasksId();
            }
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

    /** Добавление подзадачи */
    public void setSubtask(Subtask subtask) {
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);

        epics.get(subtask.getEpicId()).setSubtaskId(subtask.getId());
        updateEpic(epics.get(subtask.getEpicId()));
    }

    /** Обновление подзадачи
     * при обновлении подзадачи так-же обновляется эпик
     * @param subtask - обновленная версия подзадачи
     * */
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateEpic(epics.get(subtask.getEpicId()));
    }

    /** Удаление по идентификатору
     * @param id - идентификатор подзадачи
     * */
    public void deleteSubtask(int id) {
        if(subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            Epic epic = epics.get(subtask.getEpicId());

            epic.getSubtasksId().remove(id);
            updateEpic(epic);

            subtasks.remove(id);
        }
    }
}
