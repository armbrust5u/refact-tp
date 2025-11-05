package org.iut.refactoring.print;

import java.util.logging.Logger;

public interface PrintService {
    Logger logger = Logger.getLogger(PrintService.class.getName());
    void afficher(String message);
    void afficherTitre(String titre);
}
