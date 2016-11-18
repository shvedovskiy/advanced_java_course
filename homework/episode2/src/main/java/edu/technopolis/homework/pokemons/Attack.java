package edu.technopolis.homework.pokemons;

public class Attack {
    private String name;
    private int damage;
    private int power;

    public Attack(String name, int damage, int power) {
        this.name = name;
        if (damage > 0 && damage <= 100) {
            this.damage = damage;
        }
        if (power > 0 && power <= 100) {
            this.power = power;
        }
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getPower() {
        return power;
    }
}
