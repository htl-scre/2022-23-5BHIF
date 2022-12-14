package at.htlstp.bhif5.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Pizza pizza = (Pizza) o;
        return id != null && Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
