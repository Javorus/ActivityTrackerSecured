package pl.javorus.activitytrackersecured.activity;


import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.user.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<?> createActivity(@RequestBody ActivityDTO activity,
                                            @AuthenticationPrincipal User user
    ) {
        return activityService.createActivity(activity, user);
    }


    @GetMapping
    public ResponseEntity<?> getActivitiesByUser(@AuthenticationPrincipal User user) {
        List<Activity> activities = activityService.getActivities(user);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/date-range")
    public ResponseEntity<?> getActivitiesByDateRange(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(activityService.getActivitiesByDateRange(start_date, end_date, user));

    }

    @GetMapping("/user/{name}")
    public ResponseEntity<Activity> getActivityByUserAndName(@PathVariable("name") String name, @AuthenticationPrincipal User user) {
        Activity activity = activityService.getActivityByUserAndName(user, name);
        if (activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{day}")
    public ResponseEntity<List<Activity>> getActivitiesByUserAndDay(@PathVariable("day") LocalDate day, @AuthenticationPrincipal User user) {
        List<Activity> activities = activityService.getActivitiesByUserAndDay(user, day);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/user/{day1}/{day2}")
    public ResponseEntity<List<Activity>> getActivitiesByUserAndDayBetween(@PathVariable("day1") LocalDate day1, @PathVariable("day2") LocalDate day2, @AuthenticationPrincipal User user) {
        List<Activity> activities = activityService.getActivitiesByUserAndDayBetween(user, day1, day2);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/user/tags")
    public ResponseEntity<List<Activity>> getActivitiesByUserIdAndTagsIn(@AuthenticationPrincipal User user, @RequestBody Collection<List<Tag>> tags) {
        List<Activity> activities = activityService.getActivitiesByUserIdAndTagsIn(user, tags);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/user/tags/query")
    public ResponseEntity<List<Activity>> getActivitiesByUserIdAndTagsQuery(@AuthenticationPrincipal User user, Integer userId, @RequestBody List<Tag> tags) {
        List<Activity> activities = activityService.getActivitiesByUserIdAndTags(user, tags);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/user/{userId}/tags/exact")
    public ResponseEntity<List<Activity>> getActivitiesWithExactTags(@AuthenticationPrincipal User user, Integer userId, @RequestBody List<Tag> tags) {
        List<Activity> activities = activityService.getActivitiesWithExactTags(user, tags, tags.size());
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/user/{userId}/tags/all")
    public ResponseEntity<List<Activity>> getActivitiesWithAllTags(@AuthenticationPrincipal User user, @RequestBody List<Tag> tags) {
        List<Activity> activities = activityService.getActivitiesWithAllTags(user, tags, tags.size());
        return ResponseEntity.ok(activities);
    }


}
