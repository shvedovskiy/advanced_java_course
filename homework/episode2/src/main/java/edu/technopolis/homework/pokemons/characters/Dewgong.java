package edu.technopolis.homework.pokemons.characters;

import edu.technopolis.homework.pokemons.Attack;
import edu.technopolis.homework.pokemons.Repulse;
import java.util.ArrayList;

public class Dewgong extends Pokemon {
    public Dewgong(String name) {
        this.name = name;
        this.health = maxHealth;
        this.power = maxPower;
        this.repulse = new Repulse("dewgong repulse", 20, 25);
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack("1", 15, 8));
        this.attacks.add(new Attack("2", 26, 25));
        this.attacks.add(new Attack("3", 30, 35));
    }

    public void trainingAttack(Attack attack) {
        this.attacks.add(attack);
    }

    public void trainingRepulse(int absorption, int power) {
        if (absorption > this.repulse.getAbsorption() && power > this.repulse.getPower()) {
            repulse = new Repulse(this.repulse.getName(), absorption, power);
        }
    }
}
