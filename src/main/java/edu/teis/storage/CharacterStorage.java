package edu.teis.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.teis.model.Character;

public class CharacterStorage {

    private final List<Character> characters = new ArrayList<>();

    public void saveAll(List<Character> newCharacters) {
        characters.clear();
        if (newCharacters != null) {
            characters.addAll(newCharacters);
        }
    }

    public List<Character> getAll() {
        return new ArrayList<>(characters);
    }

    public Optional<Character> findById(int id) {
        return characters.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    public List<Character> findByNameContains(String name) {
        if (name == null || name.isBlank()) {
            return getAll();
        }
        String lowerCaseName = name.toLowerCase();
        return characters.stream()
                .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());
    }

    public List<Character> filterByGender(String gender) {
        if (gender == null || gender.isBlank()) {
            return getAll();
        }
        String lowerCaseGender = gender.toLowerCase();
        return characters.stream()
                .filter(c -> c.getGender() != null && c.getGender().toLowerCase().equals(lowerCaseGender))
                .collect(Collectors.toList());
    }

    public List<Character> filterByStatus(String status) {
        if (status == null || status.isBlank()) {
            return getAll();
        }
        String lowerCaseName = status.toLowerCase();
        return characters.stream()
                .filter(c -> c.getStatus() != null && c.getStatus().toLowerCase().equals(lowerCaseName))
                .collect(Collectors.toList());
    }

    public List<Character> filterByOrigin(String origin) {
        if (origin == null || origin.isBlank()) {
            return getAll();
        }
        String lowerCaseOrigin = origin.toLowerCase();
        return characters.stream()
                .filter(c -> c.getOrigin() != null && c.getOrigin().toLowerCase().equals(lowerCaseOrigin))
                .collect(Collectors.toList());
    }

    public void clear() {
        characters.clear();
    }

    public int size() {
        return characters.size();
    }
}
