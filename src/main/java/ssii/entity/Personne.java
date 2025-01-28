package ssii.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    @NonNull
    private String nom;
    private String prenom;
    private String poste;

    @ManyToOne
    private Personne superieur;

    @OneToMany(mappedBy = "superieur")
    private ArrayList<Personne> subordonnes = new ArrayList<>();

    @OneToMany(mappedBy = "personnes")
    private ArrayList<Participation> participations = new ArrayList<>();

}
