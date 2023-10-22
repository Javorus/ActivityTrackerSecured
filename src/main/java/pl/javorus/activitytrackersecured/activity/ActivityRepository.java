package pl.javorus.activitytrackersecured.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.user.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Optional<Activity> findById(Integer id);


    List<Activity> findAllByUser(User user);

    Activity findByUserAndName(User user, String name);

    List<Activity> findAllByUserAndDay(User user, LocalDate day);

    List<Activity> findAllByUserAndDayBetween(User user, LocalDate day, LocalDate day2);

    List<Activity> findAllByUserAndTagsIn(User user, Collection<List<Tag>> tags);

    @Query("SELECT a FROM Activity a JOIN a.tags t WHERE a.user.id = :userId AND t IN :tags")
    List<Activity> findAllByUserIdAndTags(@Param("user") User user, @Param("tags") List<Tag> tags);

    @Query("SELECT a FROM Activity a JOIN a.tags t WHERE a.user = :user AND t IN :tags GROUP BY a HAVING COUNT(t) = :size")
    List<Activity> findActivitiesWithExactTags(@Param("user") User user, @Param("tags") List<Tag> tags, @Param("size") long size);

    @Query("SELECT a FROM Activity a JOIN a.tags t WHERE a.user.id = :userId AND t IN :tags GROUP BY a HAVING COUNT(t) = :size")
    List<Activity> findActivitiesWithAllTags(@Param("user") User user, @Param("tags") List<Tag> tags, @Param("size") long size);

}
