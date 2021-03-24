package ro.dragomiralin.gateway.security;

import java.util.List;

public class CorsConstants {
    public static final List<String> ALLOWED_ORIGINS = List.of("http://localhost:4200");
    public static final List<String> ALLOWED_METHODS = List.of("*");

    private CorsConstants(){

    }
}