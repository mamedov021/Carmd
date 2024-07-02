package com.example.carapi;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CarRecognitionService {
    private static final String API_URL = "https://api.carmd.com/v3.0/decode";
    private static final String API_KEY = "Basic OWRhZjMyOTkTtMzkzYS00N1luLWFlZmEtMzI0MzQxNmM4OGM5";
    private static final String PARTNER_TOKEN = "3d4f55c25640d47d58e0c37895782aa08";

    public Map<String, Object> getCarInfo(String imagePath) throws IOException {
        File imageFile = new File(imagePath);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(API_URL);
            uploadFile.addHeader("Authorization", "Bearer " + API_KEY);
            uploadFile.addHeader("Partner-Token", PARTNER_TOKEN);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", imageFile, ContentType.APPLICATION_OCTET_STREAM, imageFile.getName());
            HttpEntity multipart = builder.build();

            uploadFile.setEntity(multipart);
            try (CloseableHttpResponse response = httpClient.execute(uploadFile)) {
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

                return new HashMap<>();
            }
        }
    }
}
