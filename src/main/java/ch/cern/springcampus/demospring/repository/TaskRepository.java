package ch.cern.springcampus.demospring.repository;

import ch.cern.springcampus.demospring.bean.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAllByDoneEquals(boolean done);
}
