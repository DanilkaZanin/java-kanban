import manager.TaskManager;
import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task t1 = new Task("Задача1", "Описание1", Status.NEW);
        Task t2 = new Task("Задача2", "Описание2", Status.NEW);

        Epic epic1 = new Epic("Эпик1","Описание1");
        Epic epic2 = new Epic("Эпик2","Описание2");

        Subtask subtask1ForEpic1 = new Subtask("Сабтаск1","Описание1", Status.NEW, epic1);
        Subtask subtask2ForEpic1 = new Subtask("Сабтаск2","Описание2", Status.NEW, epic1);

        Subtask subtask1ForEpic2 = new Subtask("Сабтаск3","Описание3", Status.NEW, epic2);

        epic1.setSubtask(subtask1ForEpic1);
        epic1.setSubtask(subtask2ForEpic1);

        epic2.setSubtask(subtask1ForEpic2);

        taskManager.setTask(t1);
        taskManager.setTask(t2);
        taskManager.setEpic(epic1);
        taskManager.setEpic(epic2);



        List<Task> tasks = taskManager.getAllTasks();
        List<Epic> epics = taskManager.getListOfAllEpics();

        System.out.println("Задачи");
        for(Task task : tasks){
            System.out.println(task);
        }

        System.out.println("Эпики");

        for(Epic epic : epics){
            System.out.println(epic);
        }
    }
}
