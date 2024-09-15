package libers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class AltLinuxApi {
    private static final String API_URL = "https://rdb.altlinux.org/api/export/branch_binary_packages/";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public AltLinuxApi(){
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public List<PackageInfo> getPackages(String branch) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + branch))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        List<PackageInfo> packages = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(responseBody);

        if (rootNode.isArray()) {
            packages = mapper.convertValue(rootNode, new TypeReference<List<PackageInfo>>() {});
        } else if (rootNode.isObject()) {
            System.err.println("Обрабатываем объект.");
            JsonNode packagesNode = rootNode.get("packages"); // Замените "packages" на правильное имя поля
            if (packagesNode != null && packagesNode.isArray()) {
                packages = mapper.convertValue(packagesNode, new TypeReference<List<PackageInfo>>() {});
            }
        } else {
            System.err.println("Неизвестный формат данных.");
        }

        try (FileWriter fileWriter = new FileWriter("output_" + branch + ".json")) {
            fileWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(packages));
        }

        return packages;
    }
}
