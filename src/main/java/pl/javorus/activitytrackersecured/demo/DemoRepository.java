package pl.javorus.activitytrackersecured.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javorus.activitytrackersecured.user.User;

import java.util.List;
import java.util.Optional;

public interface DemoRepository  extends JpaRepository<Demo, Integer> {
    Optional<Demo> findByName(String name);
    void deleteById(Integer id);

    List<Demo> findAllByUser(User user);
}
