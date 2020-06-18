package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import web.model.User;

import java.net.URI;
import java.util.List;

@Service
public class UserServiceRest implements UserService {

    private   RestTemplate restTemplate  ;
    private   String serverUrl;

    private final HttpHeaders httpHeaders;
    @Autowired
    public UserServiceRest(RestTemplate restTemplate,
                           @Value("${application.server.url}") String serverUrl,
                           HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
        this.httpHeaders = httpHeaders;
    }


    @Override
    public User findByUsername(String username) {
        HttpEntity<User>httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<User>responseEntity = restTemplate.exchange(
                serverUrl + "/api/"+username,
                HttpMethod.GET,
                httpEntity,
                User.class
        );
        return responseEntity.getBody();
    }

    @Override
    public List<User> findAllUsers() {
HttpEntity<User>httpEntity = new HttpEntity<>(httpHeaders);
       return restTemplate.exchange(
                serverUrl + "/api",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();
    }

    @Override
    public User addUser(User user)  {

        HttpEntity<User>httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<User>responseEntity = restTemplate.exchange(
                serverUrl+"/api",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<User>() {}
        );
        return responseEntity.getBody();
    }

    @Override
    public void deleteUser(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serverUrl+"/api/delete")
                .queryParam("id", id);

        HttpEntity<User>httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
               builder.build().toUri(),
                HttpMethod.POST,
                httpEntity,
                Long.class
        );
        responseEntity.getBody();

    }

    @Override
    public User updateUser(User user) {
        HttpEntity<User>httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<User>responseEntity = restTemplate.exchange(
                serverUrl + "/api",
                HttpMethod.PUT,
                httpEntity,
                User.class
        );
        return responseEntity.getBody();

    }

    @Override
    public User getById(Long id) {
        HttpEntity<User>httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<User>responseEntity = restTemplate.exchange(
                serverUrl + "/api/id/" + id,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<User>(){}
        );
        return responseEntity.getBody();

    }

    @Override
    public User getByUsernameAndPasswordService(String username, String password) {
        return null;
    }


}
