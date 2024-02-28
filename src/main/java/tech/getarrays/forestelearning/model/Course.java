package tech.getarrays.forestelearning.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@NamedQuery(name = "Course.getAllCourses", query = "select new tech.getarrays.forestelearning.wrapper.CourseWrapper(c.id,c.title,c.htmlDescription,c.imageUrl) from Course c")
@NamedQuery(name = "Course.findById", query = "select c from Course c where c.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "title")
    private String title;

    @Lob
    @Column(name= "htmlDescription")
    private String htmlDescription;

    @Column(name= "imageUrl")
    private String imageUrl;
}
