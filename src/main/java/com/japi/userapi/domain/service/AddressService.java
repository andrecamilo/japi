package com.japi.userapi.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.japi.userapi.domain.model.Address;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class AddressService {
    public Address getAddressByCep(String zipCode) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + zipCode + "/json/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Erro ao consultar CEP: " + responseCode);
            }

            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(inline.toString());

            Address address = new Address();
            address.setZipCode(node.path("cep").asText());
            address.setStreet(node.path("logradouro").asText());
            address.setNeighborhood(node.path("bairro").asText());
            address.setCity(node.path("localidade").asText());
            address.setState(node.path("uf").asText());
            address.setComplement(node.path("complemento").asText());
            return address;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar endereço", e);
        }
    }
}
