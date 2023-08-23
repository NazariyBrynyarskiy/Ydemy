package pet.prjct.ydemy.ydemy.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

}
