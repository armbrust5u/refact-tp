package org.iut.refactoring.service;

import org.iut.refactoring.calcul.CalculBonus;
import org.iut.refactoring.calcul.CalculSalaire;
import org.iut.refactoring.calcul.CalculSalaireFactory;
import org.iut.refactoring.employe.Employe;

import java.util.HashMap;
import java.util.Map;

public class SalaireService {
    private final Map<String, Double> cacheSalaires = new HashMap<>();

    public double calculerSalaire(Employe employe) {
        CalculSalaire calcul = CalculSalaireFactory.getCalculs(employe.getType());
        return calcul.calculerSalaire(employe);
    }

    public double calculerBonus(Employe employe) {
        CalculBonus calculeur = CalculSalaireFactory.getCalculBonus(employe.getType());

        if (calculeur == null) {
            return 0.0;
        }

        return calculeur.calculerBonus(employe);
    }

    public void mettreAJourCache(Employe employe) {
        double salaire = calculerSalaire(employe);
        cacheSalaires.put(employe.getId(), salaire);
    }
}