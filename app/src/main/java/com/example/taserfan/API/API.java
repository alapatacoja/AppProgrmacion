package com.example.taserfan.API;

public class API {
    public static class Routes {
        // Oracle
        public static final String AUTHENTICATE = "/authenticate";
        public static String IP = "http://192.168.1.130";
        public static String PUERTO = "4567";
        public final static String URL = IP+":"+PUERTO;
//        public final static String URL = "http://192.168.1.130:4567/api/";

        public static final String COCHE = "/coche";
        public static final String MOTO = "/moto";
        public static final String BICI = "/bici";
        public static final String PATINETE = "/patinete";
        public static final String VEHICULO = "/vehiculo";
    }


}
