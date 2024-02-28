package tech.getarrays.forestelearning.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "ProgressUser.getAllProgress", query = "select new tech.getarrays.forestelearning.wrapper.ProgressWrapper(c.id,c.quiz,c.user,c.grade) from ProgressUser c")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "progress")
public class ProgressUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "nota")
    private Integer grade;
}
