package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role  {


    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users ;

    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    // получить Орган власти



    public Role(String role) {
        this.role = role;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
