import code.manager.Managers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagersTest {
   @Test
   public void shouldReturnNewInstanceOfInMemoryTaskManager() {
       assertNotNull(Managers.getDefault());
   }

   @Test
    public void shouldReturnNewInstanceOfInMemoryHistoryManager() {
       assertNotNull(Managers.getDefaultHistory());
   }
}
