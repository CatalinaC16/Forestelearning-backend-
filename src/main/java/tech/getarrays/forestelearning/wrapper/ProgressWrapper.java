package tech.getarrays.forestelearning.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressWrapper {

    private Long id;
    private Quiz quiz;
    private User user;
    private Integer grade;
}
