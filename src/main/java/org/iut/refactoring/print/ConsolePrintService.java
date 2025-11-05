package org.iut.refactoring.print;

public class ConsolePrintService implements PrintService {
    @Override
    public void afficher(String message) {
        logger.info(message);
    }

    @Override
    public void afficherTitre(String titre) {
        logger.info("=== " + titre + " ===");
    }
}
