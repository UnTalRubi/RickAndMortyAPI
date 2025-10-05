package edu.teis.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.teis.model.Character;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CharacterRepository {

    private static final String API_URL = "https://rickandmortyapi.com/api/character/";

    private final ObjectMapper objectMapper;

    public CharacterRepository() {
        this.objectMapper = new ObjectMapper();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public List<Character> fetchCharacters() throws IOException{
        URL url = new URL(API_URL);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        InputStream inputStream = connection.getInputStream();
        StringBuilder responseBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
        } finally {
            connection.disconnect();
        }

        String responseBody = responseBuilder.toString();

        PageResponse pageResponse = objectMapper.readValue(responseBody, PageResponse.class);

        return pageResponse.getResults();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PageResponse {
        private List<Character> results;

        public PageResponse() {}

        public List<Character> getResults() { return results; }
        public void setResults(List<Character> results) { this.results = results; }
    }
}
