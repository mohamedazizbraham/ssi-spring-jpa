package ssii.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ssii.dao.PersonneRepository;
import ssii.dao.ProjetRepository;
import ssii.service.ParticipationService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ParticipationServiceTest {
    @Autowired
    private ParticipationService participationService;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @Test
    void participationUnique() {
        // Test de la règle 1
        assertThrows(RuntimeException.class, () -> {
            participationService.ajouterParticipation(1, 1, "Développeur", 50f);
            participationService.ajouterParticipation(1, 1, "Chef projet", 30f);
        });
    }

    @Test
    void totalPourcentage() {
        // Test de la règle 2
        assertThrows(RuntimeException.class, () -> {
            participationService.ajouterParticipation(1, 1, "Développeur", 60f);
            participationService.ajouterParticipation(1, 2, "Analyste", 50f);
        });
    }

    @Test
    void nombreProjetsMax() {
        // Test de la règle 3
        assertThrows(RuntimeException.class, () -> {
            participationService.ajouterParticipation(1, 1, "Développeur", 30f);
            participationService.ajouterParticipation(1, 2, "Analyste", 30f);
            participationService.ajouterParticipation(1, 3, "Testeur", 30f);
            participationService.ajouterParticipation(1, 4, "Chef projet", 10f);
        });
    }
}