package tech.getarrays.forestelearning.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Quiz.getAllQuizes", query = "select new tech.getarrays.forestelearning.wrapper.QuizWrapper(c.id,c.title,c.course,c.htmlDescription,c.correctOptions) from Quiz c")
@NamedQuery(name = "Quiz.findById", query = "select c from Quiz c where c.id=:id")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "quiz")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Lob
    @Column(name= "htmlDescription")
    private String htmlDescription;

    private String correctOptions;
}
