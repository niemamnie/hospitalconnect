package de.hackathon.hospitalconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalconnectApplication {

    public static void main(String[] args) {
        if (args.length >= 4) {
            System.setProperty("javax.net.ssl.trustStore", args[0]);
            System.setProperty("javax.net.ssl.trustStorePassword", args[1]);
            System.setProperty("-Djavax.net.ssl.keyStore", args[2]);
            System.setProperty("-Djavax.net.ssl.trustStorePassword", args[3]);
        }
        SpringApplication.run(HospitalconnectApplication.class, args);
    }

}
