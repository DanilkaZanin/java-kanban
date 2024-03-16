import code.manager.InMemoryTaskManager;
import code.manager.TaskManager;
import code.status.Status;
import code.tasks.Epic;
import code.tasks.Subtask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/** Что должны делать Эпики и сабтаски.
 * Возвращать список.
 * Возвращать по id.
 * Удалять все задачи.
 * Добавлять задачу.
 * Обновлять задачу. - переделать
 * Удалять задачу. - переделать
 * Получать подзадачи из эпика - Доделать
 * */

public class InMemoryTaskManagerEpicsTest {
    private TaskManager taskManager;
    private Epic epic1;
    private Epic epic2;
    private Subtask subtask1ForEpic1;
    private Subtask subtask2ForEpic1;

    @BeforeEach
    public void preparing() {
        taskManager = new InMemoryTaskManager();

        epic1 = new Epic("Эпик1","Описание1");
        epic2 = new Epic("Эпик2","Описание2");

        taskManager.setEpic(epic1);
        taskManager.setEpic(epic2);

        subtask1ForEpic1 = new Subtask("Сабтаск1","Описание1", Status.NEW, epic1.getId());
        subtask2ForEpic1 = new Subtask("Сабтаск2","Описание2", Status.NEW, epic1.getId());

        taskManager.setSubtask(subtask1ForEpic1);
        taskManager.setSubtask(subtask2ForEpic1);

    }

    @Test
    public void shouldReturnArrayOfEpics() {
        ArrayList<Epic> epics = taskManager.getEpics();
        ArrayList<Epic> epics1 = new ArrayList<>(Arrays.asList(epic1,epic2));

        assertEquals(epics,epics1);
    }

    @Test
    public void shouldReturnArrayOfSubtasks() {
        ArrayList<Subtask> subtasks = taskManager.getSubtasks();
        ArrayList<Subtask> subtasks1 = new ArrayList<>(Arrays.asList
                (subtask1ForEpic1,
                subtask2ForEpic1));

        assertEquals(subtasks,subtasks1);
    }

    @Test
    public void shouldReturnEpicByItsId() {
        assertEquals(taskManager.getEpic(epic1.getId()), epic1);
    }

    @Test
    public void shouldReturnSubtaskByItsId() {
        assertEquals(taskManager.getSubtask(subtask1ForEpic1.getId()),subtask1ForEpic1);
    }

    @Test
    public void shouldDeleteAllEpics() {
        taskManager.deleteEpics();
        assertEquals(taskManager.getEpics(), new ArrayList<>());
    }

    @Test
    public void shouldDeleteAllSubtasks() {
        taskManager.deleteSubtasks();
        assertEquals(taskManager.getSubtasks(), new ArrayList<>());
    }

    @Test
    public void shouldAddEpic() {
        Epic epic = new Epic("AAA", "bbb");
        taskManager.setEpic(epic);

        assertEquals(taskManager.getEpic(epic.getId()), epic);
    }

    @Test
    public void shouldAddSubtask() {
        Subtask subtask = new Subtask("name", "desc", epic1.getId());
        taskManager.setSubtask(subtask);

        assertEquals(taskManager.getSubtask(subtask.getId()), subtask);
    }

    @Test
    public void shouldUpdateEpicStatusWithAddingSubtask() {
        Subtask subtask = new Subtask("name", "desc", Status.IN_PROGRESS, epic1.getId());
        taskManager.setSubtask(subtask);

        assertEquals(taskManager.getEpic(epic1.getId()).getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void shouldUpdateEpicStatusWithChangingSubtasksStatus() {
        Subtask subtask = taskManager.getSubtask(subtask1ForEpic1.getId());
        subtask.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask);
        //Переделать
        assertEquals(Status.IN_PROGRESS, taskManager.getEpic(epic1.getId()).getStatus());
    }

    @Test
    public void shouldReturnNullAfterDeleteEpic() {
        taskManager.deleteEpic(epic1.getId());

        assertNull(taskManager.getEpic(epic1.getId()));
    }

    @Test
    public void shouldReturnNullAfterDeleteSubtask() {
        Subtask subtask = taskManager.getSubtasks().get(0);
        taskManager.deleteSubtask(subtask.getId());

        assertNull(taskManager.getSubtask(subtask.getId()));
    }
}
