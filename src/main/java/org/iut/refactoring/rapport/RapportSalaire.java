package org.iut.refactoring.rapport;

import org.iut.refactoring.calcul.CalculSalaire;
import org.iut.refactoring.calcul.CalculSalaireFactory;
import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.print.PrintService;

import java.util.List;

public class RapportSalaire implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre, PrintService printService) {
        for (Employe emp : employes) {
            if (filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre)) {
                CalculSalaire calcul = CalculSalaireFactory.getCalculs(emp.getType());
                double salaire = calcul.calculerSalaire(emp);
                printService.afficher(emp.getNom() + ": " + salaire + " €");
            }
        }
    }
}