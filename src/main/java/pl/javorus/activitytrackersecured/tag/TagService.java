package pl.javorus.activitytrackersecured.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.javorus.activitytrackersecured.tag.Tag;
import pl.javorus.activitytrackersecured.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository ;

    public ResponseEntity<?> save(TagDTO tagD, User user) {
        Optional<Tag> tagIn = tagRepository.findByNameAndUser(tagD.name(), user);
        if (tagIn.isPresent()) {
            return ResponseEntity.status(409).body("Tag already exists");
        }

        Tag tag = Tag.builder()
                .name(tagD.name())
                .user(user)
                .build();
        if(tagRepository.save(tag).equals(tag)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(500).body("Error");

    }
    public List<Tag> getAllUserTagsFromList(List<Tag> tags, User user) {
        return tagRepository.findAllByUserAndIds(tags.stream().map(Tag::getId).collect(Collectors.toList()),  user);
    }
    public ResponseEntity<?> getAllUserTags(User user) {
        List<Tag> tags = tagRepository.findAllByUser(user);
        return ResponseEntity.ok(tags);
    }

    public ResponseEntity<?> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return ResponseEntity.ok(tags);
    }

    public List<Tag> getManybyUser(List<Tag> tags, User user) {
        return null;
    }
}
