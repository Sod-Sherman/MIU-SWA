package shop.shopping.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.net.URI;

@Service
@Log4j2
public class RestService {
    @Autowired
    private RestOperations restTemplate;


    public ResponseEntity<String> doExchangeRest(Object object, URI url, HttpMethod method) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> requestEntity = new HttpEntity<>(object, requestHeaders);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
            System.out.println("\n====================in doExchangeRest method=====================================================================================");
            System.out.println("Requested HTTP URL: " + url + " Method: " + method);
            System.out.println("RequestBody: " + object);
            System.out.println("ResponseEntity status: " + responseEntity.getStatusCode());
            System.out.println("ResponseEntity body: " + responseEntity.getBody());
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println(e);
        }
        return responseEntity;
    }

    public <T> T getValueOfRestCall(URI url, HttpMethod method, Object bodyObject, Class<T> tClass) throws IOException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> responseEntity = doExchangeRest(bodyObject, url, method);
            if (responseEntity == null) {
                log.error("ResponseEntity is null for: {}", url);
                return null;
            }
            return objectMapper.readValue(responseEntity.getBody(), tClass);
        }catch (Exception e) {
            log.error(e);
        }
       return null;
    }
}
