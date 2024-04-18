package com.stockapi.StockAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Document
public class User implements UserDetails {
    @Id
    private String id;
    // non null
    private String name;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add((GrantedAuthority) role::getRoleName));
        return authorities;
    }

        @Override @JsonIgnore public String getUsername() {
            return email;
        }

        @Override @JsonIgnore public boolean isAccountNonExpired() {
            return false;
        }

        @Override @JsonIgnore public boolean isAccountNonLocked () {
            return false;
        }

        @Override @JsonIgnore public boolean isCredentialsNonExpired () {
            return false;
        }

        @Override @JsonIgnore public boolean isEnabled () {
            return false;
        }
    }


