package com.laurus.wmcs.security;

import lombok.Data;

@Data
public class JwtResponse {
    private static final long serialVersionUID = 1L;
    private String jwtToken;
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
