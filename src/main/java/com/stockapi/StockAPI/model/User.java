package com.stockapi.StockAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@AllArgsConstructor
@Data
public class User {
    private String name;
    private String email;
    private String password;
    private Role role;
}


