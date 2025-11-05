package org.iut.refactoring.calcul;

import java.util.HashMap;
import java.util.Map;

public class CalculSalaireFactory {
    private static final Map<String, CalculSalaire> calculs = new HashMap<>();

    static {
        calculs.put("DEVELOPPEUR", new CalculSalaireDeveloppeur());
        calculs.put("CHEF DE PROJET", new CalculSalaireChefProjet());
        calculs.put("STAGIAIRE", new CalculSalaireStagiaire());
    }

    public static CalculSalaire getCalculs(String type) {
        return calculs.getOrDefault(type, new CalculSalaireDefaut());
    }

    public static CalculBonus getCalculBonus(String type) {
        CalculSalaire calcul = calculs.get(type);
        if (calcul instanceof CalculBonus) {
            return (CalculBonus) calcul;
        }
        return null;
    }
}