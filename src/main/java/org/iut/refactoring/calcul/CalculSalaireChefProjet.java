package org.iut.refactoring.calcul;

import org.iut.refactoring.employe.Employe;

public class CalculSalaireChefProjet implements CalculSalaire, CalculBonus {
    private static final double MULTIPLICATEUR_BASE = 1.5;
    private static final double MULTIPLICATEUR_EXPERIENCE = 1.1;
    private static final int SEUIL_EXPERIENCE = 3;
    private static final double BONUS_FIXE = 5000;
    private static final double TAUX_BONUS = 0.2;
    private static final double MULTIPLICATEUR_BONUS_EXPERIENCE = 1.3;

    @Override
    public double calculerSalaire(Employe employe) {
        double salaire = employe.getSalaireDeBase() * MULTIPLICATEUR_BASE;

        if (employe.getExperience() > SEUIL_EXPERIENCE) {
            salaire *= MULTIPLICATEUR_EXPERIENCE;
        }

        salaire += BONUS_FIXE;

        return salaire;
    }

    @Override
    public double calculerBonus(Employe employe) {
        double bonus = employe.getSalaireDeBase() * TAUX_BONUS;

        if (employe.getExperience() > SEUIL_EXPERIENCE) {
            bonus *= MULTIPLICATEUR_BONUS_EXPERIENCE;
        }

        return bonus;
    }
}