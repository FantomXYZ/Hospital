package by.agsr.consoleapp.service;

import by.agsr.consoleapp.client.PatientClient;
import by.agsr.consoleapp.dto.PatientDto;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientClient patientClient;
    private final Faker faker = new Faker(new Locale("en"));

    public void addPatients() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        for (int i = 0; i < 100; i++) {
            String gender = faker.options().option("male", "female");
            String name = faker.name().firstName();
            String birthDate = LocalDateTime.ofInstant(
                    faker.date().birthday(20, 80).toInstant(),
                    java.time.ZoneId.systemDefault()
            ).format(dateTimeFormatter);


            patientClient.createPatient(
                    PatientDto.builder()
                            .gender(gender)
                            .birthDate(birthDate)
                            .name(name)
                            .build()
            );
        }
    }
}
