package tech.getarrays.forestelearning.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseWrapper {

    private Long id;
    private String title;
    private String htmlDescription;
    private String imageUrl;


}
