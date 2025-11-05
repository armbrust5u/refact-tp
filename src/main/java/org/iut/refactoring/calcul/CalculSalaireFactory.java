package org.iut.refactoring.calcul;

import java.util.HashMap;
import java.util.Map;

public class CalculSalaireFactory {
    private static final Map<String, CalculSalaire> calcul = new HashMap<>();

    static {
        calcul.put("DEVELOPPEUR", new CalculSalaireDeveloppeur());
        calcul.put("CHEF DE PROJET", new CalculSalaireChefProjet());
        calcul.put("STAGIAIRE", new CalculSalaireStagiaire());
    }

    public static CalculSalaire getCalcul(String type) {
        return calcul.getOrDefault(type, new CalculSalaireDefaut());
    }
}