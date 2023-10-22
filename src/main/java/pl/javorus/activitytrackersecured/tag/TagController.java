package pl.javorus.activitytrackersecured.tag;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.javorus.activitytrackersecured.demo.DemoDTO;
import pl.javorus.activitytrackersecured.demo.DemoService;
import pl.javorus.activitytrackersecured.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
public class TagController {
    private final TagService tagService;;
    @GetMapping
    @RequestMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping
    @RequestMapping
    public ResponseEntity<?> getAllTags(@AuthenticationPrincipal User user) {
           return  tagService.getAllTags();
    }
    @GetMapping
    @RequestMapping("/user")
    public ResponseEntity<?> getAllUserTags(@AuthenticationPrincipal User user) {
        return  tagService.getAllUserTags(user);
    }
    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDTO tag, @AuthenticationPrincipal User user) {
        return tagService.save(tag, user);
    }
}
