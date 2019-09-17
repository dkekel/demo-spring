package ch.cern.springcampus.demospring.service;

import ch.cern.springcampus.demospring.bean.Task;
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

    public void addNewTask(final String text) {
        Task newTask = new Task();
        newTask.setId(UUID.randomUUID().toString());
        newTask.setText(text);
        taskRepository.save(newTask);
    }
}
