package com.example.FM6.dto;

import java.util.List;

public class RegistrationRequest {
    public String adherentName;
    public String adherentEmail;
    public String adherentPassword;

    public List<UserRequest> adjacents;
    public List<UserRequest> enfants;

    public static class UserRequest {
        public String name;
        public String email;
        public String password;
    }
}
