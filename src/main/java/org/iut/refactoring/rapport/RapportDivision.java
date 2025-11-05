package org.iut.refactoring.rapport;

import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.print.PrintService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RapportDivision implements GenerateurRapport {
    @Override
    public void generer(List<Employe> employes, String filtre, PrintService ps) {
        HashMap<String, Integer> compteurDivisions = new HashMap<>();
        for (Employe emp : employes) {
            String div = emp.getEquipe();
            compteurDivisions.put(div, compteurDivisions.getOrDefault(div, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : compteurDivisions.entrySet()) {
            ps.afficher(entry.getKey() + ": " + entry.getValue() + " employés");
        }
    }
}