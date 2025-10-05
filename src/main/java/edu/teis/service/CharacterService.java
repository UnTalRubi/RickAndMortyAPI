package edu.teis.service;

import edu.teis.repository.CharacterRepository;
import edu.teis.storage.CharacterStorage;
import edu.teis.model.Character;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CharacterService {
    private final CharacterRepository repository;
    private final CharacterStorage storage;

    public CharacterService(CharacterRepository repository, CharacterStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    public void loadCharacters() throws IOException {
        List<Character> characters = repository.fetchCharacters();
        storage.saveAll(characters);
        System.out.println("\n\t|Rick & Morty API Loaded|\n");
    }

    public List<Character> getAllCharacters() {
        return storage.getAll();
    }

    public Optional<Character> findById(int id) {
        return storage.findById(id);
    }

    public List<Character> findByName(String name) {
        return storage.findByNameContains(name);
    }

    public List<Character> findByGender(String gender) {
        return storage.filterByGender(gender);
    }

    public List<Character> findByOrigin(String origin) {
        return storage.filterByOrigin(origin);
    }

    public List<Character> filterByStatus(String status) {
        return storage.filterByStatus(status);
    }

    public void saveToJson(String filePath) throws IOException {
        storage.exportToJson(filePath);
    }
}
