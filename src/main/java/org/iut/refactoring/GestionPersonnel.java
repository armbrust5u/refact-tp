package org.iut.refactoring;

import java.util.*;
import java.time.*;

public class GestionPersonnel {

    private final EmployeRepository employeRepository = new EmployeRepository();
    public HashMap<String, Double> salairesEmployes = new HashMap<>();
    public ArrayList<String> logs = new ArrayList<>();

    public void ajouteSalarie(String type, String nom, double salaireDeBase, int experience, String equipe) {
        Employe emp = new Employe(type, nom, salaireDeBase, experience, equipe);
        employeRepository.ajouter(emp);

        double salaireFinal = salaireDeBase;
        if (type.equals("DEVELOPPEUR")) {
            salaireFinal = salaireDeBase * 1.2;
            if (experience > 5) {
                salaireFinal = salaireFinal * 1.15;
            }
        } else if (type.equals("CHEF DE PROJET")) {
            salaireFinal = salaireDeBase * 1.5;
            if (experience > 3) {
                salaireFinal = salaireFinal * 1.1;
            }
        } else if (type.equals("STAGIAIRE")) {
            salaireFinal = salaireDeBase * 0.6;
        }

        salairesEmployes.put(emp.getId(), salaireFinal);

        logs.add(LocalDateTime.now() + " - Ajout de l'employé: " + nom);
    }

    public double calculSalaire(String employeId) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            System.out.println("ERREUR: impossible de trouver l'employé");
            return 0;
        }

        Employe emp = empOpt.get();
        String type = emp.getType();
        double salaireDeBase = emp.getSalaireDeBase();
        int experience = emp.getExperience();

        double salaireFinal = salaireDeBase;
        if (type.equals("DEVELOPPEUR")) {
            salaireFinal = salaireDeBase * 1.2;
            if (experience > 5) {
                salaireFinal = salaireFinal * 1.15;
            }
            if (experience > 10) {
                salaireFinal = salaireFinal * 1.05; // bonus
            }
        } else if (type.equals("CHEF DE PROJET")) {
            salaireFinal = salaireDeBase * 1.5;
            if (experience > 3) {
                salaireFinal = salaireFinal * 1.1;
            }
            salaireFinal = salaireFinal + 5000; // bonus
        } else if (type.equals("STAGIAIRE")) {
            salaireFinal = salaireDeBase * 0.6;
            // Pas de bonus pour les stagiaires
        } else {
            salaireFinal = salaireDeBase;
        }
        return salaireFinal;
    }

    public void generationRapport(String typeRapport, String filtre) {
        System.out.println("=== RAPPORT: " + typeRapport + " ===");

        if (typeRapport.equals("SALAIRE")) {
            for (Employe emp : employeRepository.obtenirTous()) {
                if (filtre == null || filtre.isEmpty() ||
                        emp.getEquipe().equals(filtre)) {
                    String id = emp.getId();
                    String nom = emp.getNom();
                    double salaire = calculSalaire(id);
                    System.out.println(nom + ": " + salaire + " €");
                }
            }
        } else if (typeRapport.equals("EXPERIENCE")) {
            for (Employe emp : employeRepository.obtenirTous()) {
                if (filtre == null || filtre.isEmpty() ||
                        emp.getEquipe().equals(filtre)) {
                    String nom = emp.getNom();
                    int exp = emp.getExperience();
                    System.out.println(nom + ": " + exp + " années");
                }
            }
        } else if (typeRapport.equals("DIVISION")) {
            HashMap<String, Integer> compteurDivisions = new HashMap<>();
            for (Employe emp : employeRepository.obtenirTous()) {
                String div = emp.getEquipe();
                compteurDivisions.put(div, compteurDivisions.getOrDefault(div, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : compteurDivisions.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " employés");
            }
        }
        logs.add(LocalDateTime.now() + " - Rapport généré: " + typeRapport);
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

        logs.add(LocalDateTime.now() + " - Employé promu: " + emp.getNom());
        System.out.println("Employé promu avec succès!");
    }

    public List<Employe> getEmployesParDivision(String division) {
        return employeRepository.trouverParEquipe(division);
    }

    public void printLogs() {
        System.out.println("=== LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public double calculBonusAnnuel(String employeId) {
        Optional<Employe> empOpt = employeRepository.trouverParId(employeId);
        if (empOpt.isEmpty()) {
            return 0;
        }

        Employe emp = empOpt.get();
        String type = emp.getType();
        int experience = emp.getExperience();
        double salaireDeBase = emp.getSalaireDeBase();

        double bonus = 0;
        if (type.equals("DEVELOPPEUR")) {
            bonus = salaireDeBase * 0.1;
            if (experience > 5) {
                bonus = bonus * 1.5;
            }
        } else if (type.equals("CHEF DE PROJET")) {
            bonus = salaireDeBase * 0.2;
            if (experience > 3) {
                bonus = bonus * 1.3;
            }
        } else if (type.equals("STAGIAIRE")) {
            bonus = 0; // Pas de bonus
        }
        return bonus;
    }

    // Getter pour maintenir la compatibilité avec les tests
    public List<Employe> getEmployes() {
        return employeRepository.obtenirTous();
    }
}