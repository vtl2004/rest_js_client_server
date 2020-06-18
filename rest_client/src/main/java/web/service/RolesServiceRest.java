package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.Role;

import java.util.List;
@Service
public class RolesServiceRest implements RoleService {

    private  RestTemplate restTemplate ;
    private  String serverUrl;
    private final HttpHeaders httpHeaders;
    @Autowired
    public RolesServiceRest(RestTemplate restTemplate, @Value("${application.server.url}")String serverUrl,
                            HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
        this.httpHeaders = httpHeaders;
    }




    @Override
    public Role findByRole(String role) {
        HttpEntity<Role>httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Role>roleResponseEntity = restTemplate.exchange(
                serverUrl+"/api/rol/"+role,
                HttpMethod.GET,
                httpEntity,
                Role.class
        );

        return roleResponseEntity.getBody();
    }

    @Override
    public List<Role> findAllRoles() {
        HttpEntity<Role>httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Role>> roleResponseEntity = restTemplate.exchange(
                serverUrl + "/api/roles",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Role>>() {
                }
        );
        return roleResponseEntity.getBody();


    }
}
