package at.htlstp.bhif5.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true)
    @Positive
    private long number;

    @ManyToOne
    private SchoolClass schoolClass;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
