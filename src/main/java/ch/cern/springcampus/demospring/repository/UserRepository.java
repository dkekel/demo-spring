package ch.cern.springcampus.demospring.repository;

import ch.cern.springcampus.demospring.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
