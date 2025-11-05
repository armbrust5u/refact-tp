package org.iut.refactoring.log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogService {
    private final List<String> logs = new ArrayList<>();

    public void ajouterLog(String message) {
        logs.add(LocalDateTime.now() + " - " + message);
    }

    public List<String> obtenirLogs() {
        return Collections.unmodifiableList(logs);
    }

    public void afficherLogs() {
        System.out.println("=== LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}