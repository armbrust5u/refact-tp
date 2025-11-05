package org.iut.refactoring;

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
        assertEquals(1, gp.employes.size());
        assertEquals(1, gp.salairesEmployes.size());
        assertFalse(gp.logs.isEmpty());

        Object[] emp = gp.employes.getFirst();
        double salaire = gp.salairesEmployes.get(emp[0]);
        assertEquals(4140, salaire, 0.01);
    }

    @Test
    void testAjoutSalaireChefDeProjet() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 4, "Interne");
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.salairesEmployes.get(emp[0]);
        assertEquals(6600, salaire, 0.01);
    }

    @Test
    void testAjoutSalaireStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.salairesEmployes.get(emp[0]);
        assertEquals(1200, salaire, 0.01);
    }

    @Test
    void testAjoutSalaireAutreType() {
        gp.ajouteSalarie("INCONNU", "Noah", 2500, 2, "Interne");
        Object[] emp = gp.employes.getFirst();
        assertEquals(2500, gp.salairesEmployes.get(emp[0]));
    }

    @Test
    void testCalculSalaireDeveloppeurAvecBonus() {
        gp.ajouteSalarie("DEVELOPPEUR", "Noah", 3000, 11, "Interne");
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.calculSalaire((String) emp[0]);
        assertEquals(4347.0, salaire, 0.1);
    }

    @Test
    void testCalculSalaireChefDeProjetAvecBonus() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 5, "Interne");
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.calculSalaire((String) emp[0]);
        assertEquals(11600, salaire, 0.01);
    }

    @Test
    void testCalculSalaireStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.calculSalaire((String) emp[0]);
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
        Object[] emp = gp.employes.getFirst();
        gp.avancementEmploye((String) emp[0], "DEVELOPPEUR");
        assertEquals("DEVELOPPEUR", emp[1]);
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
        List<Object[]> devs = gp.getEmployesParDivision("Interne");
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
        Object[] emp = gp.employes.getFirst();
        double bonus = gp.calculBonusAnnuel((String) emp[0]);
        assertEquals(450, bonus, 0.01);
    }

    @Test
    void testCalculBonusChefDeProjet() {
        gp.ajouteSalarie("CHEF DE PROJET", "Noah", 4000, 4, "Interne");
        Object[] emp = gp.employes.getFirst();
        double bonus = gp.calculBonusAnnuel((String) emp[0]);
        assertEquals(1040, bonus, 0.01);
    }

    @Test
    void testCalculBonusStagiaire() {
        gp.ajouteSalarie("STAGIAIRE", "Noah", 2000, 1, "Interne");
        Object[] emp = gp.employes.getFirst();
        double bonus = gp.calculBonusAnnuel((String) emp[0]);
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
        Object[] emp = gp.employes.getFirst();
        double salaire = gp.calculSalaire((String) emp[0]);
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
