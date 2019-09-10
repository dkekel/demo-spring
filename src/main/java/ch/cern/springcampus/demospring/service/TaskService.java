package ch.cern.springcampus.demospring.service;

import ch.cern.springcampus.demospring.bean.Task;
import ch.cern.springcampus.demospring.exception.TaskNotFoundException;
import ch.cern.springcampus.demospring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskService {

    private transient final TaskRepository taskRepository;

    @Autowired
    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = Objects.requireNonNull(taskRepository);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "createdOn"));
    }

    public List<Task> findAllTasksNotCompleted() {
        return taskRepository.findAllByDoneEquals(false);
    }

    public void addNewTask(final String text) {
        Task newTask = new Task();
        newTask.setId(UUID.randomUUID().toString());
        newTask.setText(text);
        taskRepository.save(newTask);
    }

    public void updateTask(final String id, final boolean done) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        task.setDone(done);
        taskRepository.save(task);
    }

    public void deleteTask(final String id) {
        taskRepository.deleteById(id);
    }

    public long countIncompleteTasks(final List<Task> tasks) {
        return tasks.size() - tasks.stream().filter(Task::isDone).count();
    }
}
