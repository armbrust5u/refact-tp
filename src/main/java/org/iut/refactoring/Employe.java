package org.iut.refactoring;

import java.util.UUID;

public class Employe {
    private String id;
    private String type;
    private String nom;
    private double salaireDeBase;
    private int experience;
    private String equipe;

    public Employe(String type, String nom, double salaireDeBase, int experience, String equipe) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.nom = nom;
        this.salaireDeBase = salaireDeBase;
        this.experience = experience;
        this.equipe = equipe;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public int getExperience() {
        return experience;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
}