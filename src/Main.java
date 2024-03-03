import code.manager.TaskManager;
import code.status.Status;
import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;
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



        List<Task> tasks = taskManager.getTasks();
        List<Epic> epics = taskManager.getEpics();

        //печать списков
        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);

        //меняю статус
        Task t3 = new Task("Задача1", "Описание1", Status.IN_PROGRESS);

        Epic epic3 = new Epic("Эпик1","Описание1");
        Subtask subtask3 = new Subtask("Сабтаск1","Описание1", Status.DONE, epic1);
        epic3.setSubtask(subtask3);
        epic3.setSubtask(subtask2ForEpic1);

        //обновление задачи
        taskManager.setTask(t3);
        taskManager.setEpic(epic3);

        tasks = taskManager.getTasks();
        epics = taskManager.getEpics();

        System.out.println("После смены статуса");

        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);

        //Удаление задачи и эпика
        int subtaskSize1 = taskManager.getSubtasks().size();

        taskManager.deleteTask(t2.hashCode());
        taskManager.deleteEpic(epic2.getId());

        tasks = taskManager.getTasks();
        epics = taskManager.getEpics();
        int subtaskSize2 = taskManager.getSubtasks().size();

        System.out.println("После удаления");

        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);
        System.out.println("Было :" + subtaskSize1 + " Стало: " + subtaskSize2);

    }

    public static <T> void print(List<T> tasks){
        for (T task : tasks){
            System.out.println(task);
        }
    }
}
