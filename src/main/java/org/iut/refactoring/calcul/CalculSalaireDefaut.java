package org.iut.refactoring.calcul;

import org.iut.refactoring.employe.Employe;

public class CalculSalaireDefaut implements CalculSalaire {
    @Override
    public double calculerSalaire(Employe employe) {
        return employe.getSalaireDeBase();
    }

    @Override
    public double calculerBonus(Employe employe) {
        return 0.0;
    }
}