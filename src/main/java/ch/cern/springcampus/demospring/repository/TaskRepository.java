package ch.cern.springcampus.demospring.repository;

import ch.cern.springcampus.demospring.bean.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
