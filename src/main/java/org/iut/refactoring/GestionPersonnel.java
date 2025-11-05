package org.iut.refactoring;

import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.employe.EmployeRepository;
import org.iut.refactoring.exception.EmployeNotFoundException;
import org.iut.refactoring.exception.RapportNotFoundException;
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
        Employe emp = employeRepository.trouverParId(employeId)
                .orElseThrow(() -> new EmployeNotFoundException("Employé non trouvé avec l'ID: " + employeId));

        return salaireService.calculerSalaire(emp);
    }

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        GenerateurRapport generateur = GenerateurRapportFactory.getRapport(typeRapport);
        if (generateur == null) {
            throw new RapportNotFoundException("Type de rapport inconnu: " + typeRapport);
        }

        generateur.generer(employeRepository.obtenirTous(), filtre);

        logService.ajouterLog("Rapport généré: " + typeRapport);
    }

    public void avancementEmploye(String employeId, String newType) {
        Employe emp = employeRepository.trouverParId(employeId)
                .orElseThrow(() -> new EmployeNotFoundException("Employé non trouvé avec l'ID: " + employeId));

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
        Employe emp = employeRepository.trouverParId(employeId)
                .orElseThrow(() -> new EmployeNotFoundException("Employé non trouvé avec l'ID: " + employeId));

        return salaireService.calculerBonus(emp);
    }

    public List<Employe> getEmployes() {
        return employeRepository.obtenirTous();
    }

    public List<String> getLogs() {
        return logService.obtenirLogs();
    }
}