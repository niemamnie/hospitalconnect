package de.hackathon.hospitalconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalconnectApplication {

    public static void main(String[] args) {
        if (args.length >= 2) {
            System.setProperty("javax.net.ssl.trustStore", args[0]);
            System.setProperty("javax.net.ssl.trustStorePassword", args[1]);
        }
        SpringApplication.run(HospitalconnectApplication.class, args);
    }

}
