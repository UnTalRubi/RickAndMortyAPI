package edu.teis.app;

import edu.teis.model.Character;
import edu.teis.model.Origin;
import edu.teis.repository.CharacterRepository;
import edu.teis.service.CharacterService;
import edu.teis.storage.CharacterStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        CharacterRepository repo = new CharacterRepository();
        CharacterStorage storage = new CharacterStorage();
        CharacterService service = new CharacterService(repo, storage);


        try {
            service.loadCharacters();
        } catch (IOException e) {
            System.err.println("Error al cargar la API: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n\tEscoge una opción:\n");
            System.out.println("1. Mostrar los personajes");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Buscar por estado");
            System.out.println("4. Buscar por origen");
            System.out.println("5. Buscar por género");
            System.out.println("6. Exportar personajes");
            System.out.println("7. Salir");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    System.out.println("\n|| Nombre, género e imagen de personaje ||\n");
                    service.getAllCharacters()
                            .forEach(c -> System.out.println(c.getId() + "\t|\t" + c.getName() + "\t|\t" + c.getGender() + "\t|\t" + c.getImage()));
                    break;
                case "2":
                    System.out.print("Nombre a buscar: ");
                    String name = scanner.nextLine();
                    System.out.println("\n|| Personajes con el nombre buscado ||\n");
                    List<Character> byName = service.findByName(name);
                    byName.forEach(c -> System.out.println(c.getId() + "\t|\t" + c.getName()));
                    break;
                case "3":
                    System.out.print("Elige un estado a buscar [Alive / Dead / unknown]: ");
                    String status = scanner.nextLine();
                    System.out.println("\n|| Personajes con estado buscado ||\n");
                    List<Character> byStatus = service.filterByStatus(status);
                    byStatus.forEach(c -> System.out.println(c.getId() + "\t|\t" + c.getName() + "\t|\t" + c.getStatus()));
                    break;
                case "4":
                    System.out.print("Origen a buscar: ");
                    String originInput = scanner.nextLine().toLowerCase();
                    List<Character> byOrigin = service.findByOrigin(originInput);
                    byOrigin.forEach(c -> System.out.println(c.getId() + "\t|\t" + c.getName() + "\t|\t" + c.getOrigin()));
                    break;
                case "5":
                    System.out.print("Elige un género a buscar [Male / Female]: ");
                    String genderInput = scanner.nextLine().toLowerCase();
                    List<Character> byGender = service.findByGender(genderInput);
                    byGender.forEach(c -> System.out.println(c.getId() + "\t|\t" + c.getName() + "\t|\t" + c.getGender()));
                    break;
                case "6":
                    try {
                        service.saveToJson("RickAndMortyCharacters.json");
                    } catch (IOException e) {
                        System.err.println("Error al exportar: " + e.getMessage());
                    }
                    break;
                case "7":
                    running = false;
                    break;
                default:
                    System.out.println("Escoge del 1 al 7.");
            }
        }

        System.out.println("Cerrando aplicación...");
        scanner.close();
    }
}
