package pl.javorus.activitytrackersecured.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.javorus.activitytrackersecured.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {
    private final DemoService demoService;;
    @GetMapping
    @RequestMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping
    public ResponseEntity<?> getAllUserDemos(@AuthenticationPrincipal User user) {
        return demoService.getAllUserDemos(user);
    }
    @PostMapping
    public ResponseEntity<?> createDemo(@RequestBody DemoDTO demo,
                                        @AuthenticationPrincipal User user
    ) {
        return demoService.save(demo, user);
    }
}
