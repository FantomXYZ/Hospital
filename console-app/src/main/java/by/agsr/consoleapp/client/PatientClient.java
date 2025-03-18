package by.agsr.consoleapp.client;

import by.agsr.consoleapp.config.FeignConfig;
import by.agsr.consoleapp.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hospital-service", url = "${HOSPITAL_SERVICE_URL}", configuration = FeignConfig.class)
public interface PatientClient {
    @PostMapping("/api/patients")
    ResponseEntity<PatientDto> createPatient(PatientDto patientDto);

}
