package org.iut.refactoring.rapport;

import org.iut.refactoring.employe.Employe;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RapportDivision implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre) {
        HashMap<String, Integer> compteurDivisions = new HashMap<>();
        for (Employe emp : employes) {
            String div = emp.getEquipe();
            compteurDivisions.put(div, compteurDivisions.getOrDefault(div, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : compteurDivisions.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " employés");
        }
    }
}