package com.kaikan.infra.models;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String access_token;
    private String user_role;

    public AuthenticationResponse(String token, String user_role) {
        this.access_token = token;
        this.user_role=user_role;
    }




}
