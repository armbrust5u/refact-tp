package org.iut.refactoring.rapport;

import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.print.PrintService;

import java.util.List;

public class RapportExperience implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre, PrintService ps) {
        for (Employe emp : employes) {
            if (filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre)) {
                ps.afficher(emp.getNom() + ": " + emp.getExperience() + " années");
            }
        }
    }
}