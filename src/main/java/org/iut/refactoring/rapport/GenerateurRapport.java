package org.iut.refactoring.rapport;

import org.iut.refactoring.employe.Employe;
import org.iut.refactoring.print.PrintService;

import java.util.List;

public interface GenerateurRapport {
    void generer(List<Employe> employes, String filtre, PrintService ps);
}