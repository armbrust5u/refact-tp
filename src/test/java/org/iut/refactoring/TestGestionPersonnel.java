package org.iut.refactoring;

import org.iut.refactoring.employe.Employe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestGestionPersonnel {

    private GestionPersonnel gp;

    @BeforeEach
    void setUp() {
        gp = new GestionPersonnel();
    }

    @Test
    void testAjoutSalaireDeveloppeur() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 6, "Interne");
        assertEquals(1, gp.getEmployes().size());
        assertEquals(1, gp.salairesEmployes.size());
        assertFalse(gp.logs.isEmpty());

        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.salairesEmployes.get(emp.getId());
        assertEquals(4140, salaire, 0.01);
    }

    void testAjoutSalaireChefDeProjet() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 4, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.salairesEmployes.get(emp.getId());
        assertEquals(6600, salaire, 0.01);
    }

    @Test
    void testAjoutSalaireStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.salairesEmployes.get(emp.getId());
        assertEquals(1200, salaire, 0.01);
    }

    @Test
    void testAjoutSalaireAutreType() {
        gp.ajouteSalarie("INCONNU", "Noah", 2500, 2, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        assertEquals(2500, gp.salairesEmployes.get(emp.getId()));
    }

    @Test
    void testCalculSalaireDeveloppeurAvecBonus() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 11, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.calculSalaire(emp.getId());
        assertEquals(4347.0, salaire, 0.1);
    }

    @Test
    void testCalculSalaireChefDeProjetAvecBonus() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 5, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.calculSalaire(emp.getId());
        assertEquals(11600, salaire, 0.01);
    }

    @Test
    void testCalculSalaireStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.calculSalaire(emp.getId());
        assertEquals(1200, salaire, 0.01);
    }

    @Test
    void testCalculSalaireEmployeInconnu() {
        double salaire = gp.calculSalaire("FAUX_ID");
        assertEquals(0, salaire);
    }

    @Test
    void testGenerationRapportSalaire() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 2, "Interne");
        gp.generationRapport("SALAIRE", "Interne");
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }

    @Test
    void testGenerationRapportExperience() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 4, "Management");
        gp.generationRapport("EXPERIENCE", null);
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }

    @Test
    void testGenerationRapportDivision() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        gp.ajouteSalarie("CHEF DE PROJET", "Loup", 4000, 2, "Management");
        gp.generationRapport("DIVISION", "");
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }

    @Test
    void testAvancementEmployeExiste() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 1800, 1, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        gp.avancementEmploye(emp.getId(), "DEVELOPPEUR");
        assertEquals("DEVELOPPEUR", emp.getType());
    }

    @Test
    void testAvancementEmployeInconnu() {
        gp.avancementEmploye("ID_FAUX", "CHEF DE PROJET");
        assertTrue(true);
    }

    @Test
    void testGetEmployesParDivision() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 5, "Interne");
        gp.ajouteSalarie("STAGIAIRE", "Loup", 1000, 1, "Management");
        List<Employe> devs = gp.getEmployesParDivision("Interne");
        assertEquals(1, devs.size());
    }

    @Test
    void testPrintLogs() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 2, "Interne");
        gp.printLogs();
        assertFalse(gp.logs.isEmpty());
    }

    @Test
    void testCalculBonusDeveloppeur() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 6, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double bonus = gp.calculBonusAnnuel(emp.getId());
        assertEquals(450, bonus, 0.01);
    }

    @Test
    void testCalculBonusChefDeProjet() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 4, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double bonus = gp.calculBonusAnnuel(emp.getId());
        assertEquals(1040, bonus, 0.01);
    }

    @Test
    void testCalculBonusStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Employe emp = gp.getEmployes().getFirst();
        double bonus = gp.calculBonusAnnuel(emp.getId());
        assertEquals(0, bonus, 0.01);
    }

    @Test
    void testCalculBonusEmployeInconnu() {
        double bonus = gp.calculBonusAnnuel("FAUX_ID");
        assertEquals(0, bonus);
    }

    @Test
    void testCalculSalaireTypeInconnu() {
        gp.ajouteSalarie("INCONNU", "Zoe", 2500, 2, "QA");
        Employe emp = gp.getEmployes().getFirst();
        double salaire = gp.calculSalaire(emp.getId());
        assertEquals(2500, salaire);
    }

    @Test
    void testGenerationRapportSalaireFiltreVide() {
        gp.ajouteSalarie("DEVELOPPEUR", "Alice", 3000, 2, "DevTeam");
        gp.generationRapport("SALAIRE", "");
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }

    @Test
    void testGenerationRapportSalaireAucuneCorrespondance() {
        gp.ajouteSalarie("DEVELOPPEUR", "Alice", 3000, 2, "DevTeam");
        gp.generationRapport("SALAIRE", "AutreEquipe");
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }

    @Test
    void testGenerationRapportExperienceAvecFiltre() {
        gp.ajouteSalarie("DEVELOPPEUR", "Alice", 3000, 2, "DevTeam");
        gp.ajouteSalarie("CHEF DE PROJET", "Bob", 4000, 4, "Management");
        gp.generationRapport("EXPERIENCE", "Management");
        assertTrue(gp.logs.getLast().contains("Rapport généré"));
    }
}
