## Тесты которые я не могу сделать:

### Проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;
При добавлении объекта в менеджер, программа всегда генерирует свой id.

### Проверьте, что объект Subtask нельзя сделать своим же эпиком;
Id эпика указывается вручную при создании объекта Subtask. 
У самого же сабтаска нет id до момента добавления в taskManager.