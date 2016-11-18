package edu.technopolis.homework.pokemons;

import edu.technopolis.homework.pokemons.characters.Pokemon;

import java.util.Random;

public class Trainer {
    private Pokemon pokemon;

    public Trainer(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void trainPokemon(String command, String ... value) {
        switch (command.toLowerCase()) {
            case "health":
                pokemon.trainingHealth(Integer.parseInt(value[0]));
                break;
            case "power":
                pokemon.trainingPower(Integer.parseInt(value[0]));
                break;
            case "attack":
                pokemon.trainingAttack(new Attack(value[0], Integer.parseInt(value[1]), Integer.parseInt(value[2])));
                break;
            case "repulse":
                pokemon.trainingRepulse(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
                break;
            default:
                System.out.println("Wrong command format");
        }
    }

    public int commandPokemon() {
        Random rand = new Random();
        int attacksNum = pokemon.getAttacks().size();
        int num = rand.nextInt(attacksNum + 1);
        int damage = pokemon.fight(Integer.toString(num));
        return damage;
    }

}
