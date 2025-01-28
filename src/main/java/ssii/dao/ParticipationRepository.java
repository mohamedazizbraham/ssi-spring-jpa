package ssii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ssii.entity.Participation;
import ssii.entity.Projet;
import ssii.entity.Personne;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    ArrayList<Participation> findByPersonneAndProjet(Personne personne,Projet projet);
    ArrayList<Participation> findByPersonneAndProjetFinAfterOrProjetFinIsNull(Personne personne, LocalDate date);
    int countByPersonneAndProjetFinAfterOrProjetFinIsNull(Personne personne, LocalDate date);
}
