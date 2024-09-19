package libers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

        List<PackageInfo> packageList = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(responseBody);

        if (rootNode.isObject()) {
            JsonNode packagesNode = rootNode.get("packages");
            if (packagesNode != null && packagesNode.isArray()) {
                for (JsonNode packageNode : packagesNode) {
                    PackageInfo packageData = new PackageInfo(
                            packageNode.get("name").asText(),
                            packageNode.get("epoch").asInt(),
                            packageNode.get("version").asText(),
                            packageNode.get("release").asText(),
                            packageNode.get("arch").asText(),
                            packageNode.get("disttag").asText(),
                            packageNode.get("buildtime").asLong(),
                            packageNode.get("source").asText()
                    );
                    packageList.add(packageData);
                }
            }
        } else {
            System.err.println("Неизвестный формат данных.");
        }

        return packageList;
    }
}
