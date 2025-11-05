package org.iut.refactoring.calcul;

import org.iut.refactoring.employe.Employe;

public class CalculSalaireChefProjet implements CalculSalaire {
    private static final double MULTIPLICATEUR_BASE = 1.5;
    private static final double BONUS_EXPERIENCE = 1.1;
    private static final double BONUS_FIXE = 5000.0;
    private static final double TAUX_BONUS = 0.2;
    private static final double MULTIPLICATEUR_BONUS_SENIOR = 1.3;

    @Override
    public double calculerSalaire(Employe employe) {
        double salaire = employe.getSalaireDeBase() * MULTIPLICATEUR_BASE;

        if (employe.getExperience() > 3) {
            salaire *= BONUS_EXPERIENCE;
        }

        salaire += BONUS_FIXE;

        return salaire;
    }

    @Override
    public double calculerBonus(Employe employe) {
        double bonus = employe.getSalaireDeBase() * TAUX_BONUS;

        if (employe.getExperience() > 3) {
            bonus *= MULTIPLICATEUR_BONUS_SENIOR;
        }

        return bonus;
    }
}