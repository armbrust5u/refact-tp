package org.iut.refactoring;

import org.iut.refactoring.calcul.CalculBonus;
import org.iut.refactoring.calcul.CalculSalaire;
import org.iut.refactoring.calcul.CalculSalaireFactory;
import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.employe.EmployeRepository;
import org.iut.refactoring.rapport.GenerateurRapport;
import org.iut.refactoring.rapport.GenerateurRapportFactory;
import org.iut.refactoring.log.LogService;

import java.util.*;

public class GestionPersonnel {

    private final EmployeRepository employeRepository = new EmployeRepository();
    public HashMap<String, Double> salairesEmployes = new HashMap<>();
    private final LogService logService = new LogService();

    public void ajouteSalarie(String type, String nom, double salaireDeBase, int experience, String equipe) {
        Employe emp = new Employe(type, nom, salaireDeBase, experience, equipe);
        employeRepository.ajouter(emp);

        CalculSalaire calculs = CalculSalaireFactory.getCalculs(type);
        double salaireFinal = calculs.calculerSalaire(emp);

        salairesEmployes.put(emp.getId(), salaireFinal);

        logService.ajouterLog("Ajout de l'employé: " + nom);
    }

    public double calculSalaire(String employeId) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return 0;
        }

        Employe emp = empOpt.get();

        CalculSalaire calcul = CalculSalaireFactory.getCalculs(emp.getType());
        return calcul.calculerSalaire(emp);
    }

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        GenerateurRapport generateur = GenerateurRapportFactory.getRapport(typeRapport);
        if (generateur != null) {
            generateur.generer(employeRepository.obtenirTous(), filtre);
        }

        logService.ajouterLog("Rapport généré: " + typeRapport);
    }

    public void avancementEmploye(String employeId, String newType) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return;
        }

        Employe emp = empOpt.get();
        emp.setType(newType);

        double nouveauSalaire = calculSalaire(employeId);
        salairesEmployes.put(employeId, nouveauSalaire);

        logService.ajouterLog("Employé promu: " + emp.getNom());
        System.out.println("Employé promu avec succès!");
    }

    public List<Employe> getEmployesParDivision(String division) {
        return employeRepository.trouverParEquipe(division);
    }

    public void printLogs() {
        logService.afficherLogs();
    }

    public double calculBonusAnnuel(String employeId) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            return 0;
        }

        Employe emp = empOpt.get();

        CalculBonus calculeur = CalculSalaireFactory.getCalculBonus(emp.getType());

        if (calculeur == null) {
            return 0.0;
        }

        return calculeur.calculerBonus(emp);
    }

    // Getter pour maintenir la compatibilité avec les tests
    public List<Employe> getEmployes() {
        return employeRepository.obtenirTous();
    }

    // Getter pour accéder aux logs si nécessaire pour les tests
    public List<String> getLogs() {
        return logService.obtenirLogs();
    }
}