package edu.technopolis.homework.pokemons.characters;

import edu.technopolis.homework.pokemons.Attack;
import edu.technopolis.homework.pokemons.Repulse;
import java.util.*;

public class Vulpix extends Pokemon {
    public Vulpix(String name) {
        this.name = name;
        this.health = maxHealth;
        this.power = maxPower;
        this.repulse = new Repulse("vulpix repulse", 15, 20);
        this.attacks = new ArrayList<>();
        this.attacks.add(new Attack("1", 10, 13));
        this.attacks.add(new Attack("2", 14, 15));
        this.attacks.add(new Attack("3", 30, 30));
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

