package edu.technopolis.homework.pokemons;

import edu.technopolis.homework.pokemons.characters.*;

import java.io.*;
import java.util.*;

public class Game {
    private static Trainer choose_pokemon(int number) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Formatter f = new Formatter(System.out);
        String input;
        Pokemon pokemon = null;
        Trainer trainer = null;

        System.out.println("Выберите покемона для тренера: " + number);
        f.format("%-10s %5s\n", "Pikachu", "\"1\"");
        f.format("%-10s %5s\n", "Dewgong", "\"2\"");
        f.format("%-10s %5s\n", "Nidoqueen", "\"3\"");
        f.format("%-10s %5s\n", "Venomoth", "\"4\"");
        f.format("%-10s %5s\n", "Vulpix", "\"5\"");
        input = br.readLine();

        switch (input) {
            case "1":
                pokemon = new Pikachu("my_pikachu");
                trainer = new Trainer(pokemon);
                System.out.println("Тренер " + number + " выбрал \"Pikachu\"");
                break;
            case "2":
                pokemon = new Dewgong("my_dewgong");
                trainer = new Trainer(pokemon);
                System.out.println("Тренер " + number + " выбрал \"Dewdong\"");
                break;
            case "3":
                pokemon = new Nidoqueen("my_nidoqueen");
                trainer = new Trainer(pokemon);
                System.out.println("Тренер " + number + " выбрал \"Nidoqueen\"");
                break;
            case "4":
                pokemon = new Venomoth("my_venomoth");
                trainer = new Trainer(pokemon);
                System.out.println("Тренер " + number + " выбрал \"Venomoth\"");
                break;
            case "5":
                pokemon = new Vulpix("my_vulpix");
                trainer = new Trainer(pokemon);
                System.out.println("Тренер " + number + " выбрал \"Vulpix\"");
                break;
        }
        return trainer;
    }

    private static void train_pokemon(Trainer trainer) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Formatter f = new Formatter(System.out);
        String command;

        System.out.println("Выберите параметр для тренировки:");
        f.format("%-17s %-10s\n", "Атака", "\"Attack\"");
        f.format("%-17s %-10s\n", "Противодействие", "\"Repulse\"");
        f.format("%-17s %-10s\n", "Здоровье", "\"Health\"");
        f.format("%-17s %-10s\n", "Выносливость", "\"Power\"");
        command = br.readLine();

        StringTokenizer st;

        switch (command) {
            case "Attack":
                System.out.println("Введите наименование атаки, величину урона и требуемую энергию:");
                st = new StringTokenizer(br.readLine(), ",");
                trainer.trainPokemon(command, st.nextToken(), st.nextToken(), st.nextToken());
                break;
            case "Repulse":
                System.out.println("Введите величину поглощения урона и требуемую энергию:");
                st = new StringTokenizer(br.readLine(), ",");
                trainer.trainPokemon(command, st.nextToken(), st.nextToken());
                break;
            case "Health":
                System.out.println("Введите величину здоровья:");
                String health = br.readLine();
                trainer.trainPokemon(command, health);
                break;
            case "Power":
                System.out.println("Введите величину выносливости:");
                String power = br.readLine();
                trainer.trainPokemon(command, power);
                break;
        }
    }

    private static void fight(Trainer trainer_1, Trainer trainer_2) {
        System.out.println("Сражаются покемоны " + trainer_1.getPokemon().getName() + " тренера 1 и " +
        trainer_2.getPokemon().getName() + " тренера 2");
        if (trainer_1.getPokemon().getName().equals(trainer_2.getPokemon().getName())) {
            System.out.println("Одинаковые покемоны!");
            return;
        }
        boolean step_for_first = true;
        int i = 0;
        while (trainer_1.getPokemon().isAlive() &&
                trainer_1.getPokemon().isStillPowered() &&
                trainer_2.getPokemon().isAlive() &&
                trainer_2.getPokemon().isStillPowered()) {
            i++;
            if (step_for_first) {
                trainer_2.getPokemon().protect(trainer_1.commandPokemon());

                step_for_first = false;
            } else {
                trainer_1.getPokemon().protect(trainer_2.commandPokemon());
                step_for_first = true;
            }
            System.out.println("\nНа шаге " + i + ":");
            short_info(trainer_1);
            short_info(trainer_2);
        }
        System.out.print("Бой окончен. ");
        if (!trainer_1.getPokemon().isAlive() || !trainer_1.getPokemon().isStillPowered()) {
            System.out.println("Победил покемон первого тренера!");
        } else if (!trainer_2.getPokemon().isAlive() || !trainer_2.getPokemon().isStillPowered()) {
            System.out.println("Победил покемон второго тренера!");
        }

    }

    private static void relax(Trainer trainer) {
        trainer.getPokemon().relax();
    }

    private static void info(Trainer trainer) {
        Pokemon tr_pokemon = trainer.getPokemon();
        System.out.println("Покемон " + tr_pokemon.getName() +
                "\n\tЗдоровье: " + tr_pokemon.getHealth() +
                "\n\tЭнергия: " + tr_pokemon.getPower() +
                "\n\tПротиводействие: \"" + tr_pokemon.getRepulse().getName() + "\", поглощаемый урон: " +
                tr_pokemon.getRepulse().getAbsorption() + ", требуемая энергия: " +
                tr_pokemon.getRepulse().getPower() +
                "\n\tАтака:");
        for (Attack at : tr_pokemon.getAttacks()) {
            System.out.println("\t\t\"" + at.getName() + "\", урон: " + at.getDamage() +
                    ", требуемая энергия: " + at.getPower());
        }
    }

    private static void short_info(Trainer trainer) {
        Pokemon tr_pokemon = trainer.getPokemon();
        System.out.println("Покемон " + tr_pokemon.getName() +
                "\n\tЗдоровье: " + tr_pokemon.getHealth() +
                "\n\tЭнергия: " + tr_pokemon.getPower());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Formatter f = new Formatter(System.out);
        String input;

        Trainer trainer_1 = null;
        Trainer trainer_2 = null;

        System.out.println("ВЫ В ИГРЕ \"БИТВА ПОКЕМОНОВ\"!\n");
        do {
            System.out.println("\t\tВыберите действие:");
            f.format("%-32s %8s\n", "Выбрать покемона", "\"1\"");
            f.format("%-32s %8s\n", "Тренировать покемона", "\"2\"");
            f.format("%-32s %8s\n", "Вступить в бой", "\"3\"");
            f.format("%-32s %8s\n", "Дать покемону отдохнуть", "\"4\"");
            f.format("%-32s %8s\n", "Получить информацию о покемоне", "\"5\"");
            f.format("%-32s %8s\n", "Выйти из игры", "\"exit\"");
            input = br.readLine();

            switch (input) {
                case "1":
                    trainer_1 = choose_pokemon(1);
                    trainer_2 = choose_pokemon(2);
                    break;

                case "2":
                    System.out.println("Тренировка. Введите номер тренера: \"1\" или \"2\"");
                    input = br.readLine();
                    if (input.equals("1")) {
                        train_pokemon(trainer_1);
                    } else {
                        train_pokemon(trainer_2);
                    }
                    break;

                case "3":
                    if (trainer_1 == null || trainer_2 == null) {
                        System.out.println("Выберите покемонов!");
                        continue;
                    }
                    System.out.print("Бой! ");
                    fight(trainer_1, trainer_2);
                    System.out.println("Нажмите любую клавишу...");
                    br.readLine();
                    break;

                case "4":
                    System.out.println("Отдых. Введите номер тренера: \"1\" или \"2\"");
                    input = br.readLine();
                    if (input.equals("1")) {
                        relax(trainer_1);
                    } else {
                        relax(trainer_2);
                    }
                    break;

                case "5":
                    System.out.println("Информация о покемонах. Введите номер тренера: \"1\" или \"2\"");
                    input = br.readLine();

                    if (input.equals("1")) {
                        info(trainer_1);
                    } else {
                        info(trainer_2);
                    }
                    System.out.println("Нажмите любую клавишу для выхода...");
                    br.readLine();
                    break;
            }
        } while (!input.equals("exit"));
    }
}
