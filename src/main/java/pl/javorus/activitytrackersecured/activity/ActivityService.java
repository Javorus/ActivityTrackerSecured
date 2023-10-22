package pl.javorus.activitytrackersecured.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.tag.TagRepository;
import pl.javorus.activitytrackersecured.tag.TagService;
import pl.javorus.activitytrackersecured.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final TagService tagService;

    public ResponseEntity<?> createActivity(ActivityDTO activityDTO, User user) {
        List<Tag> tags = tagService.getAllUserTagsFromList(activityDTO.tags(), user);
        if(user==null){
            return ResponseEntity.badRequest().build();
        }

        Activity activity = Activity.builder()
                .name(activityDTO.name())
                .description(activityDTO.description())
                .duration(activityDTO.duration())
                .day(activityDTO.day())
                .tags(tags)
                .user(user)
                .build();

        activityRepository.save(activity);
        return ResponseEntity.ok(activity);
    }

    public List<Activity> getActivities(User user) {
        return  activityRepository.findAllByUser(user);
    }

    public Activity getActivityByUserAndName(User user, String name) {
        return activityRepository.findByUserAndName(user, name);
    }

    public List<Activity> getActivitiesByUserAndDay(User user, LocalDate day) {
        return activityRepository.findAllByUserAndDay(user, day);
    }

    public List<Activity> getActivitiesByUserAndDayBetween(User user, LocalDate day1, LocalDate day2) {
            return activityRepository.findAllByUserAndDayBetween(user, day1, day2);
    }

    public List<Activity> getActivitiesByUserIdAndTagsIn(User user, Collection<List<Tag>> tags) {
        return activityRepository.findAllByUserAndTagsIn(user, tags);
    }

    public List<Activity> getActivitiesByUserIdAndTags(User user, List<Tag> tags) {
        return activityRepository.findAllByUserIdAndTags(user, tags);
    }

    public List<Activity> getActivitiesWithExactTags(User user, List<Tag> tags, long size) {
        return activityRepository.findActivitiesWithExactTags(user, tags, size);
    }

    public List<Activity> getActivitiesWithAllTags(User user, List<Tag> tags, int size) {
                return activityRepository.findActivitiesWithAllTags(user, tags, size);
    }

    public List<Activity> getActivitiesByDateRange(LocalDate startDate, LocalDate endDate, User user) {
        return activityRepository.findAllByUserAndDayBetween(user, startDate, endDate);
    }
    public Map<Activity, Double> getActivitiesTimePercentage(LocalDate startDate, LocalDate endDate, User user ){
        List<Activity> activities = this.getActivitiesByDateRange(startDate, endDate, user);
        
        Map<Activity, Double> activitiesTimePercentage = activities.stream()
              .collect(Collectors.toMap(
                        activity -> activity,
                        activity -> activity.getDuration().toSecondOfDay() / (double)LocalTime.MAX.toSecondOfDay()
                ));
        return activitiesTimePercentage;


    }


}
