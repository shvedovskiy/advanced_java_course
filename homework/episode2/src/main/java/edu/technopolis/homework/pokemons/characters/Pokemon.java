package edu.technopolis.homework.pokemons.characters;

import edu.technopolis.homework.pokemons.Attack;
import edu.technopolis.homework.pokemons.Repulse;
import java.util.*;

public abstract class Pokemon {
    protected String name;
    protected int health;
    protected int power;
    protected int maxHealth = 100;
    protected int maxPower = 100;
    protected Repulse repulse;
    protected ArrayList<Attack> attacks;


    /* GETTERS */
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getPower() {
        return power;
    }
    public Repulse getRepulse() {
        return repulse;
    }
    public ArrayList<Attack> getAttacks() {
        return attacks;
    }


    /* TRAININGS */
    public void trainingHealth(int health) {
        if (health > 0) {
            this.maxHealth += health;
            this.health += health;
        }
    }
    public void trainingPower(int power) {
        if (power > 0) {
            this.maxPower += power;
            this.power += power;
        }
    }
    abstract public void trainingAttack(Attack attack);
    abstract public void trainingRepulse(int absorption, int power);
    public void relax() {
        health = maxHealth;
        power = maxPower;
    }


    /* FIGHTS */
    public void punch(int damage) {
        health -= damage;
    }
    public boolean isAlive() {
        return getHealth() > 0;
    }
    public boolean isStillPowered() {
        return getPower() > 0;
    }
    public int fight(String command) {
        for (Attack attack : attacks) {
            if (attack.getName().equals(command)) {
                if (power >= attack.getPower()) {
                    power -= repulse.getPower();
                    return attack.getDamage();
                }
            }
        }
        return 0;
    }
    public int protect(int damage) {
        if (power >= repulse.getPower()) {
            if (repulse.getAbsorption() >= damage) {
                power -= repulse.getPower();
                punch(damage / 4);
                return getPower();
            }
            else {
                power -= repulse.getPower();
                punch(damage / 2);
                return power;
            }
        }
        punch(damage);
        return 0;
    }
}
