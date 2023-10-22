package pl.javorus.activitytrackersecured.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.javorus.activitytrackersecured.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoRepository demoRepository;

    public ResponseEntity<?> save(DemoDTO demo,  User user) {
        Demo demo1 = Demo.builder()
                .name(demo.name())
                .user(user)
                .build();
        demoRepository.save(demo1);
        return ResponseEntity.status(201).body(demo1);
    }


    public ResponseEntity<?> getAllUserDemos(User user) {
        List<Demo> demos = demoRepository.findAllByUser(user);
        return ResponseEntity.ok(demos);
    }
}


