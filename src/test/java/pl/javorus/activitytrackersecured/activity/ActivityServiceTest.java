package pl.javorus.activitytrackersecured.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.tag.TagService;
import pl.javorus.activitytrackersecured.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private TagService tagService;

    @InjectMocks
    private ActivityService activityService;

    private User user;
    private List<Activity> activities;

    @BeforeEach
    public void setUp() {
        user = new User();
        activities = new ArrayList<>();
        activities.add(new Activity());
        activities.add(new Activity());
    }

    @Test
    public void testGetActivitiesByUserWhenActivitiesExistThenReturnActivities() {
        when(activityRepository.findAllByUser(user)).thenReturn(activities);

        ResponseEntity<?> responseEntity = activityService.getActivities(user);

        assertEquals(ResponseEntity.ok(activities), responseEntity);
    }

    @Test
    public void testGetActivitiesByUserWhenNoActivitiesThenReturnEmptyList() {
        activities.clear();
        when(activityRepository.findAllByUser(user)).thenReturn(activities);

        ResponseEntity<?> responseEntity = activityService.getActivities(user);

        assertEquals(ResponseEntity.ok(activities), responseEntity);
    }
}