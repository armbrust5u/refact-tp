package org.iut.refactoring.rapport;

import java.util.HashMap;
import java.util.Map;

public class GenerateurRapportFactory {

    private GenerateurRapportFactory() {

    }

    private static final Map<String, GenerateurRapport> rapports = new HashMap<>();

    static {
        rapports.put("SALAIRE", new RapportSalaire());
        rapports.put("EXPERIENCE", new RapportExperience());
        rapports.put("DIVISION", new RapportDivision());
    }

    public static GenerateurRapport getRapport(String type) {
        return rapports.get(type);
    }
}