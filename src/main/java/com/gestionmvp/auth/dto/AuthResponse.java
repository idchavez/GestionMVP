package com.gestionmvp.auth.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message","jwt",""})
public record AuthResponse(String username,
                           String message,
                           String jwt,
                           boolean status) {
}
