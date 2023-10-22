package pl.javorus.activitytrackersecured.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.javorus.activitytrackersecured.activity.Activity;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.user.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByUser(User user);

    Optional<Tag> findByName(String name);
        Optional<Tag> findByNameAndUser(String name, User user);
    Optional<Tag> findById(Integer id);
    @Query("select t from Tag t where t.id in :ids and t.user = :user" )
    List<Tag> findAllByUserAndIds(@Param("ids") List<Integer> tagIdsList, @Param("user") User user);

}
