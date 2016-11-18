package edu.technopolis.homework.pokemons.characters;

import edu.technopolis.homework.pokemons.Attack;
import edu.technopolis.homework.pokemons.Repulse;
import java.util.*;

public class Venomoth extends Pokemon {
    public Venomoth(String name) {
        this.name = name;
        this.health = maxHealth;
        this.power = maxPower;
        this.repulse = new Repulse("venomoth repulse", 10, 5);
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack("1", 14, 10));
        this.attacks.add(new Attack("2", 26, 30));
        this.attacks.add(new Attack("3", 43, 50));
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
