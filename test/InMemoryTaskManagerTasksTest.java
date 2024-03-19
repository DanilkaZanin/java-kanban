import code.manager.task.InMemoryTaskManager;
import code.manager.task.TaskManager;
import code.status.Status;
import code.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InMemoryTaskManagerTasksTest {
    private TaskManager taskManager;
    private Task t1;
    private Task t2;

    @BeforeEach
    public void preparing(){
        taskManager = new InMemoryTaskManager();

        t1 = new Task("Задача1", "Описание1", Status.NEW);
        t2 = new Task("Задача2", "Описание2", Status.NEW);

        taskManager.setTask(t1);
        taskManager.setTask(t2);
    }
    @Test
    public void twoTasksWithSameIdShouldBeEquals(){
        assertEquals(t1,taskManager.getTask(t1.getId()));
    }

    @Test
    public void fieldsShouldNotBeChanged() {
        Task task = new Task("ААА","БББ",Status.NEW);
        taskManager.setTask(task);

        assertEquals(task, taskManager.getTask(task.getId()), "Поля не изменились!");
    }
    @Test
    public void shouldReturnArrayOfTasks() {
        ArrayList<Task> tasks = taskManager.getTasks();
        ArrayList<Task> tasks1 = new ArrayList<>(Arrays.asList(t1,t2));

        assertEquals(tasks, tasks1);
    }
    @Test
    public void shouldClearAllTasks() {
        taskManager.deleteTasks();

        ArrayList<Task> tasks = taskManager.getTasks();

        assertEquals(tasks.size(), 0);
    }

    @Test
    public void shouldGetTaskFromId() {
        Task task = taskManager.getTask(t1.getId());

        assertEquals(task, t1);
    }

    @Test
    public void shouldAddOneMoreTask() {
        Task task = new Task("AAA", "bbb", Status.IN_PROGRESS);
        taskManager.setTask(task);

        ArrayList<Task> tasks = taskManager.getTasks();

        assertEquals(tasks.size(), 3);
        assertEquals(tasks.get(2), task);
    }

    @Test
    public void shouldUpdateTask() {
        Task task = new Task("Задача1", "Описание1", Status.IN_PROGRESS);
        task.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(task);

        assertEquals(taskManager.getTask(task.getId()).getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void shouldRemoveTask() {
        taskManager.deleteTask(t1.getId());

        assertNull(taskManager.getTask(t1.getId()));
    }
}