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

    // Для эпиков

    /** Получение списка всех эпиков
     * @return Список всех эпиков
     * */
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicArrayList = new ArrayList<>();

        for(Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            epicArrayList.add(entry.getValue());
        }

        return epicArrayList;
    }

    /** Удаление всех эпиков */
    public void deleteEpics() {
        for(Map.Entry<Integer, Epic> epic : epics.entrySet()){
            for (Subtask subtask : epic.getValue().getSubtasks()){
                deleteSubtask(subtask.getId());
            }
        }
        epics.clear();
    }

    /** Получение списка всех подзадач определённого эпика
     * @param epic - Эпик, из которого требуется получить список подзадач
     * @return Список всех подзадач из эпика
     * */
    public ArrayList<Subtask> getSubtasksFromEpic(Epic epic){
        return epic.getSubtasks();
    }

    /** Получение по идентификатору */
    public Epic getEpic(int id) {
        if(epics.containsKey(id))
            return epics.get(id);
        return null;
    }

    /** Создание и обновления эпика */
    public void setEpic(Epic epic) {
        if (epic.getSubtasks().isEmpty())
            epic = new Epic(epic, Status.NEW);
        else {
            int countOfNewTasks = 0;
            int countOfDoneTasks = 0;

            for (Subtask subtask : epic.getSubtasks()) {
                setSubtask(subtask);

                if (subtask.getStatus().equals(Status.NEW))
                    countOfNewTasks++;
                else if (subtask.getStatus().equals(Status.DONE))
                    countOfDoneTasks++;
            }

            if (countOfNewTasks == epic.getSubtasks().size())
                epic = new Epic(epic, Status.NEW);
            else if (countOfDoneTasks == epic.getSubtasks().size())
                epic = new Epic(epic, Status.DONE);
            else
                epic = new Epic(epic, Status.IN_PROGRESS);
        }
        epics.put(epic.hashCode(), epic);
    }

    /** Удаление эпика по id*/
    public void deleteEpic(int id) {
        if(epics.containsKey(id)) {
            ArrayList<Subtask> subtaskArrayList = epics.get(id).getSubtasks();

            for(Subtask subtask : subtaskArrayList){
                subtasks.remove(subtask.getId());
            }
            epics.remove(id);
        }
    }

    //Для задач

    /** Получение списка всех задач
     * @return Список всех задач
     * */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        for(Map.Entry<Integer, Task> task : tasks.entrySet()) {
            taskArrayList.add(task.getValue());
        }

        return taskArrayList;
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

    //Для подзадач

    /** Получение списка всех подзадач
     * @return  Список всех подзадач
     * */
    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();

        for(Map.Entry<Integer,Subtask> entry : subtasks.entrySet()) {
            subtaskArrayList.add(entry.getValue());
        }

        return subtaskArrayList;
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
        if (subtasks.containsKey(id)) {
            if (subtasks.get(id).getEpic() != null) {
                Epic epic = subtasks.get(id).getEpic();
                Epic newEpic = new Epic(epic.getName(), epic.getDescription());

                for (Subtask subtask : epic.getSubtasks())
                    if (subtask.getId() != id) {
                        Subtask newSubtask = new Subtask(subtask.getName(), subtask.getDescription(), newEpic);
                        setSubtask(newSubtask);

                        newEpic.setSubtask(newSubtask);
                    }


                setEpic(newEpic);
            }
        }

        subtasks.remove(id);
    }
}
