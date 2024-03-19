import code.manager.Managers;
import code.manager.task.TaskManager;
import code.status.Status;
import code.tasks.Epic;
import code.tasks.Subtask;
import code.tasks.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {
    private static TaskManager taskManager;
    @BeforeAll
    public static void preparing() {
        taskManager = Managers.getDefault();

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

    }
    //
    @Test
    public void shouldReturnNotNullWhenGettingTask() {
        //берем задачку, чтобы заполнить менеджер истории
        taskManager.getTask(taskManager.getTasks().get(0).getId());

        assertNotNull(taskManager.getHistory());
    }

    @Test
    public void shouldReturnNotNullWhenGettingEpic() {
        taskManager.getEpic(taskManager.getEpics().get(0).getId());

        assertNotNull(taskManager.getHistory());
    }

    @Test
    public void shouldReturnNotNullWhenGettingSubtask() {
        taskManager.getSubtask(taskManager.getSubtasks().get(0).getId());

        assertNotNull(taskManager.getHistory());
    }

    @Test
    public void shouldDeleteFirstValue(){
        Task t3 = new Task("Задача3", "Описание3", Status.NEW);
        Task t4 = new Task("Задача4", "Описание4", Status.NEW);
        Task t5 = new Task("Задача5", "Описание5", Status.NEW);
        Task t6 = new Task("Задача6", "Описание6", Status.NEW);
        Task t7 = new Task("Задача7", "Описание7", Status.NEW);
        Task t8 = new Task("Задача8", "Описание8", Status.NEW);
        Task t9 = new Task("Задача9", "Описание9", Status.NEW);
        Task t10 = new Task("Задача10", "Описание10", Status.NEW);
        Task t11 = new Task("Задача11", "Описание11", Status.NEW);

        taskManager.setTask(t3);
        taskManager.setTask(t4);
        taskManager.setTask(t5);
        taskManager.setTask(t6);
        taskManager.setTask(t7);
        taskManager.setTask(t8);
        taskManager.setTask(t9);
        taskManager.setTask(t10);
        taskManager.setTask(t11);

        //первое значение, которое потом удалится
        Task task = taskManager.getSubtask(0);

        for(int i = 1; i < taskManager.getTasks().size(); i++) {
            taskManager.getTask(i);
        }

        assertFalse(taskManager.getHistory().contains(task),"Первая задача удалилась из списка");
    }

    @Test
    public void shouldContainsDifferentTypes() {
        taskManager.getTask(taskManager.getTasks().get(0).getId());
        taskManager.getEpic(taskManager.getEpics().get(0).getId());
        taskManager.getSubtask(taskManager.getSubtasks().get(0).getId());

        ArrayList <Task> history = taskManager.getHistory();

        assertInstanceOf(Task.class, history.get(0), "Содержится тип Task");
        assertInstanceOf(Epic.class, history.get(1), "Содержится тип Epic");
        assertInstanceOf(Subtask.class, history.get(2), "Содержится тип Subtask");

    }

    @Test
    public void shouldContainsPreviousVersionOfTask() {
        Task task = new Task("aaa", "bbb", Status.NEW);

        taskManager.setTask(task);

        task = taskManager.getTask(task.getId());
        task.setStatus(Status.IN_PROGRESS);

        taskManager.setTask(task);

        Task newTask = taskManager.getHistory().get(0);

        assertNotEquals(newTask, task);
    }


}
