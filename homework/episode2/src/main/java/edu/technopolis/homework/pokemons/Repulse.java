package edu.technopolis.homework.pokemons;

public class Repulse {
    private String name;
    private int absorption;
    private int power;

    public Repulse(String name, int absorption, int power) {
        this.name = name;
        if (absorption > 0 && absorption <= 100) {
            this.absorption = absorption;
        }
        if (power > 0 && power <= 100) {
            this.power = power;
        }
    }

    public String getName() {
        return name;
    }

    public int getAbsorption() {
        return absorption;
    }

    public int getPower() {
        return power;
    }
}
