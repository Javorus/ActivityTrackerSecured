package pl.javorus.activitytrackersecured.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import pl.javorus.activitytrackersecured.tag.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ActivityDTO(String name, String description, LocalDate day, @JsonFormat(pattern="HH:mm:ss") LocalTime duration, List<Tag> tags) { }
