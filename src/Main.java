import manager.TaskManager;
import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task t1 = new Task("Покушать", "Не перепутать ложку с вилкой", Status.NEW);
        Subtask subtask = new Subtask("Помыть пол","Шваброй",Status.NEW);
        Epic epic = new Epic("Дела на сегодня","главное не забыть",Status.NEW);

        epic.setSubtask(subtask);

        taskManager.setTask(t1);
        taskManager.setTask(epic);

        HashMap<Integer,Task> map = taskManager.getTasks();

        for(Map.Entry<Integer,Task> entry : map.entrySet()){
            System.out.println("Ключ: "+ entry.getKey() +
                    " Имя: "+ entry.getValue().getName() +
                    " Описание: " +entry.getValue().getDescription() +
                    " Статус: " + entry.getValue().getStatus());
        }
    }
}
