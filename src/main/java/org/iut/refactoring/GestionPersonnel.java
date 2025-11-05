package org.iut.refactoring;

import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.employe.EmployeRepository;
import org.iut.refactoring.rapport.GenerateurRapport;
import org.iut.refactoring.rapport.GenerateurRapportFactory;
import org.iut.refactoring.log.LogService;
import org.iut.refactoring.service.SalaireService;

import java.util.*;

public class GestionPersonnel {

    private final EmployeRepository employeRepository = new EmployeRepository();
    private final SalaireService salaireService = new SalaireService();
    private final LogService logService = new LogService();

    public void ajouteSalarie(String type, String nom, double salaireDeBase, int experience, String equipe) {
        Employe emp = new Employe(type, nom, salaireDeBase, experience, equipe);
        employeRepository.ajouter(emp);

        salaireService.mettreAJourCache(emp);

        logService.ajouterLog("Ajout de l'employé: " + nom);
    }

    public double calculSalaire(String employeId) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return 0;
        }

        Employe emp = empOpt.get();
        return salaireService.calculerSalaire(emp);
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

        salaireService.mettreAJourCache(emp);

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
        return salaireService.calculerBonus(emp);
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