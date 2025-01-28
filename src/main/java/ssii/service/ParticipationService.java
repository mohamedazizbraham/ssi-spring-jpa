package ssii.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ssii.dao.ParticipationRepository;
import ssii.dao.PersonneRepository;
import ssii.dao.ProjetRepository;
import ssii.entity.Participation;
import ssii.entity.Personne;
import ssii.entity.Projet;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ParticipationService {
    private final PersonneRepository personneRepository;
    private final ProjetRepository projetRepository;
    private final ParticipationRepository participationRepository;

    public ParticipationService(
            PersonneRepository personneRepository,
            ProjetRepository projetRepository,
            ParticipationRepository participationRepository
    ) {
        this.personneRepository = personneRepository;
        this.projetRepository = projetRepository;
        this.participationRepository = participationRepository;
    }

    public Participation ajouterParticipation(
            Integer matricule,
            Integer codeProjet,
            String role,
            Float pourcentage
    ) {
        Personne personne = personneRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Personne non trouvée"));

        Projet projet = projetRepository.findById(codeProjet)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));

        // Règle 1: Vérification participation unique
        if (!participationRepository.findByPersonneAndProjet(personne, projet).isEmpty()) {
            throw new RuntimeException("Cette personne participe déjà à ce projet");
        }

        // Règle 2: Vérification du total des pourcentages
        List<Participation> participationsEnCours = participationRepository
                .findByPersonneAndProjetFinAfterOrProjetFinIsNull(
                        personne, LocalDate.now());

        float totalPourcentage = participationsEnCours.stream()
                .map(Participation::getPourcentage)
                .reduce(0f, Float::sum);

        if (totalPourcentage + pourcentage > 100) {
            throw new RuntimeException("Le total des participations dépasse 100%");
        }

        // Règle 3: Vérification du nombre de projets
        int nombreProjetsEnCours = participationRepository
                .countByPersonneAndProjetFinAfterOrProjetFinIsNull(
                        personne, LocalDate.now());

        if (nombreProjetsEnCours >= 3) {
            throw new RuntimeException("La personne participe déjà à 3 projets");
        }

        // Création de la participation
        Participation participation = new Participation();
        participation.setPersonne(personne);
        participation.setProjet(projet);
        participation.setRole(role);
        participation.setPourcentage(String.valueOf(pourcentage));

        return participationRepository.save(participation);
    }
}