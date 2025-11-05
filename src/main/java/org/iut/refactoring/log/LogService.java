package org.iut.refactoring.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class LogService {

    Logger logger = Logger.getLogger(getClass().getName());

    private final List<String> logs = new ArrayList<>();

    public void ajouterLog(String message) {
        logs.add(LocalDateTime.now() + " - " + message);
    }

    public List<String> obtenirLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void afficherLogs() {
        logger.info("=== LOGS ===");
        for (String log : logs) {
            logger.info(log);
        }
    }
}