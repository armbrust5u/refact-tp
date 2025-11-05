package org.iut.refactoring.employe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeRepository {
    private final ArrayList<Employe> employes = new ArrayList<>();

    public void ajouter(Employe employe) {
        employes.add(employe);
    }

    public Optional<Employe> trouverParId(String id) {
        return employes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Employe> trouverParEquipe(String equipe) {
        return employes.stream()
                .filter(e -> e.getEquipe().equals(equipe))
                .toList();
    }

    public List<Employe> obtenirTous() {
        return new ArrayList<>(employes);
    }
}