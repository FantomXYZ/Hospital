package by.agsr.consoleapp;

import by.agsr.consoleapp.service.KeycloakAuthService;
import by.agsr.consoleapp.service.PatientService;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class ConsoleAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConsoleAppApplication.class, args);

        KeycloakAuthService keycloakAuthService = context.getBean(KeycloakAuthService.class);
        PatientService patientService = context.getBean(PatientService.class);

        Authentication authToken = keycloakAuthService.getTokenFromKeycloak();
        SecurityContextHolder.getContext().setAuthentication(authToken);

        patientService.addPatients();

        int exitCode = SpringApplication.exit(context, (ExitCodeGenerator) () -> 0);
        System.exit(exitCode);
    }
}
