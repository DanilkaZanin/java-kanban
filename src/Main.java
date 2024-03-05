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

        taskManager.setTask(t1);
        taskManager.setTask(t2);


        Epic epic1 = new Epic("Эпик1","Описание1");
        Epic epic2 = new Epic("Эпик2","Описание2");


        taskManager.setEpic(epic1);
        taskManager.setEpic(epic2);

        Subtask subtask1ForEpic1 = new Subtask("Сабтаск1","Описание1", Status.NEW, epic1.getId());
        Subtask subtask2ForEpic1 = new Subtask("Сабтаск2","Описание2", Status.NEW, epic1.getId());
        Subtask subtask1ForEpic2 = new Subtask("Сабтаск3","Описание3", Status.DONE, epic2.getId());


        taskManager.setSubtask(subtask1ForEpic1);
        taskManager.setSubtask(subtask2ForEpic1);
        taskManager.setSubtask(subtask1ForEpic2);


        List<Task> tasks = taskManager.getTasks();
        List<Epic> epics = taskManager.getEpics();
        List<Subtask> subtasks = taskManager.getSubtasks();

        //печать списков
        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);

        System.out.println("Подзадачи");
        print(subtasks);

        //меняю статус
        t1.setStatus(Status.IN_PROGRESS);
        t2.setStatus(Status.DONE);
        subtask1ForEpic1.setStatus(Status.IN_PROGRESS);
        subtask1ForEpic2.setStatus(Status.IN_PROGRESS);

        //обновление задач
        taskManager.updateTask(t1);
        taskManager.updateSubtask(subtask1ForEpic1);

        tasks = taskManager.getTasks();
        epics = taskManager.getEpics();
        subtasks = taskManager.getSubtasks();

        System.out.println("\nПосле смены статуса");

        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);


        System.out.println("Подзадачи");
        print(subtasks);

        //Удаление задачи и эпика
        int subtaskSize1 = taskManager.getSubtasks().size();

        taskManager.deleteTask(t2.getId());
        taskManager.deleteEpic(epic2.getId());

        tasks = taskManager.getTasks();
        epics = taskManager.getEpics();
        subtasks = taskManager.getSubtasks();
        int subtaskSize2 = taskManager.getSubtasks().size();

        System.out.println("\nПосле удаления");

        System.out.println("Задачи");
        print(tasks);

        System.out.println("Эпики");
        print(epics);

        System.out.println("Подзадачи");
        print(subtasks);

        System.out.println("Было :" + subtaskSize1 + " Стало: " + subtaskSize2);

    }

    public static <T> void print(List<T> tasks){
        for (T task : tasks){
            System.out.println(task);
        }
    }
}
