package manager;

import status.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

import java.util.HashMap;

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
        this.tasks.put(task.hashCode(), task);
    }

    //Для подзадач

    public void setSubtask(Subtask subtask){
        subtasks.put(subtask.hashCode(), subtask);
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
