package com.stockapi.StockAPI.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}