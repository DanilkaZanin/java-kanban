import code.manager.Managers;
import code.manager.task.TaskManager;
import code.status.Status;
import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

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

        taskManager.getSubtask(subtask1ForEpic1.getId());
        taskManager.getEpic(epic1.getId());
        taskManager.getTask(t1.getId());

        printAllTasks(taskManager);
    }
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getSubtasksFromEpic(epic)) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
