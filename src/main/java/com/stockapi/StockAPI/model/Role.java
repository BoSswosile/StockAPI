package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Role {

    @Id
    private String id;
    private String roleName;

    public static Role valueOf(String role) {
        Role r = new Role();
        r.setRoleName(role);
        return r;
    }

    public enum RoleName {
        ROLE_ADMINISTRATOR,ROLE_STOREKEEPER,ROLE_VIEWER
    }

}