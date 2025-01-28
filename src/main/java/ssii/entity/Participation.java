package ssii.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String pourcentage;
    @ManyToOne

    private Personne personne;

    @ManyToOne
    private Projet projet;
}
