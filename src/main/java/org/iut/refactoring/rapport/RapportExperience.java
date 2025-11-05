package org.iut.refactoring.rapport;

import org.iut.refactoring.employe.Employe;
import java.util.List;

public class RapportExperience implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre) {
        for (Employe emp : employes) {
            if (filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre)) {
                System.out.println(emp.getNom() + ": " + emp.getExperience() + " années");
            }
        }
    }
}