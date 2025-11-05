package org.iut.refactoring.rapport;

import org.iut.refactoring.calcul.CalculSalaire;
import org.iut.refactoring.calcul.CalculSalaireFactory;
import org.iut.refactoring.employe.Employe;
import java.util.List;

public class RapportSalaire implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre) {
        for (Employe emp : employes) {
            if (filtre == null || filtre.isEmpty() || emp.getEquipe().equals(filtre)) {
                CalculSalaire calcul = CalculSalaireFactory.getCalculs(emp.getType());
                double salaire = calcul.calculerSalaire(emp);
                System.out.println(emp.getNom() + ": " + salaire + " €");
            }
        }
    }
}