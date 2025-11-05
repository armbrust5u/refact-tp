package org.iut.refactoring.calcul;

import org.iut.refactoring.employe.Employe;

public class CalculSalaireStagiaire implements CalculSalaire {
    private static final double MULTIPLICATEUR_BASE = 0.6;

    @Override
    public double calculerSalaire(Employe employe) {
        return employe.getSalaireDeBase() * MULTIPLICATEUR_BASE;
    }
}