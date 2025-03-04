package ru.itmentor.crud.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.crud.dto.User;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Getter
public class UserApiClientImpl implements UserApiClient {
    @Value("${api.base-url}")
    private String baseUrl;
    private final RestTemplate restTemplate;
    private String sessionId;

    @Override
    public List<User> getAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl, User[].class);
        sessionId = extractSessionId(response);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public String createUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, buildHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        return response.getBody();
    }

    @Override
    public String updateUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user, buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.PUT,
                request,
                String.class
        );
        return response.getBody();
    }

    @Override
    public String deleteUser(Long id) {
        HttpEntity<Void> request = new HttpEntity<>(buildHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.DELETE,
                request,
                String.class
        );
        return response.getBody();
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        if (sessionId != null) {
            headers.set("Cookie", "JSESSIONID=" + sessionId);
        }
        return headers;
    }

    private String extractSessionId(ResponseEntity<?> response) {
        String setCookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        if (setCookie != null && setCookie.contains("JSESSIONID")) {
            return setCookie.split("JSESSIONID=")[1].split(";")[0];
        }
        return null;
    }
}
