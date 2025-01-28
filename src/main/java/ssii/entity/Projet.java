package ssii.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

public class Projet {
    @Id
    private Integer code;
    private String nom;
    @Column(name="date_debut")
    private LocalDate debut;

    @Column(name="date_fin")
    private LocalDate fin;

    @OneToMany(mappedBy = "projet")
    private ArrayList<Participation> participations =new ArrayList<>();

}
