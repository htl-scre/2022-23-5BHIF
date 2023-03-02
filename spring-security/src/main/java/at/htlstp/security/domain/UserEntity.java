package at.htlstp.security.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Table(name = "users")
@Entity

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    @NotNull
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        UserEntity that = (UserEntity) o;
        return username != null && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
