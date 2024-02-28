package tech.getarrays.forestelearning.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.getarrays.forestelearning.model.Course;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizWrapper {

    private Long id;
    private String title;
    private Course course;
    private String htmlDescription;
    private String correctOption;
}
