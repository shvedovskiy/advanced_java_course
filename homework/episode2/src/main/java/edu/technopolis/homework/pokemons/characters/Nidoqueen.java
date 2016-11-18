package edu.technopolis.homework.pokemons.characters;

import edu.technopolis.homework.pokemons.Attack;
import edu.technopolis.homework.pokemons.Repulse;
import java.util.*;

public class Nidoqueen extends Pokemon {
    public Nidoqueen(String name) {
        this.name = name;
        this.health = maxHealth;
        this.power = maxPower;
        this.repulse = new Repulse("nidoqueen repulse", 19, 25);
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack("1", 5, 5));
        this.attacks.add(new Attack("2", 22, 16));
        this.attacks.add(new Attack("3", 38, 40));
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
