package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {



    private Long id;

    private String role;

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
    @Override
    public String getAuthority() {
        return role;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users = users;
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

   public Set<User> getUsers() {
        return users;
    }

     public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return  role;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role1 = (Role) o;
        return
                getRole().equals(role1.getRole()) &&
                getUsers().equals(role1.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getUsers());
    }*/
}
