package by.agsr.hospital.controllers;

import by.agsr.hospital.dto.PatientDto;
import by.agsr.hospital.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/patients")
@RequiredArgsConstructor
@Validated
@Tag(name = "Patients", description = "Methods for managing patients")
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Get patient by ID", description = "Returns patient data by their identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Patient found", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "Example patient", value = "{\"id\": \"550e8400-e29b-41d4-a716-446655440000\", \"name\": \"John Doe\", \"age\": 30, \"gender\": \"Male\"}")
            })),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable UUID id) {
        PatientDto patientDto = patientService.findById(id);
        return ResponseEntity.ok(patientDto);
    }

    @Operation(summary = "Get all patients", description = "Returns a list of all patients")
    @ApiResponse(responseCode = "200", description = "List of patients", content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(name = "Example list of patients", value = "[{\"id\": \"550e8400-e29b-41d4-a716-446655440000\", \"name\": \"John Doe\", \"age\": 30, \"gender\": \"Male\"}, {\"id\": \"660f8400-e29b-41d4-a716-446655440001\", \"name\": \"Jane Smith\", \"age\": 25, \"gender\": \"Female\"}]")
    }))
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @Operation(summary = "Create a new patient", description = "Adds a new patient to the system")
    @ApiResponse(responseCode = "201", description = "Patient created", content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(name = "Example created patient", value = "{\"id\": \"550e8400-e29b-41d4-a716-446655440002\", \"name\": \"Michael Johnson\", \"age\": 40, \"gender\": \"Male\"}")
    }))
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        PatientDto createdPatient = patientService.create(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @Operation(summary = "Update patient data", description = "Updates patient information by ID")
    @ApiResponse(responseCode = "200", description = "Patient updated", content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(name = "Example updated patient", value = "{\"id\": \"550e8400-e29b-41d4-a716-446655440000\", \"name\": \"John Doe\", \"age\": 31, \"gender\": \"Male\"}")
    }))
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto, @PathVariable UUID id) {
        PatientDto updatedPatientDto = patientService.update(id, patientDto);
        return ResponseEntity.ok(updatedPatientDto);
    }

    @Operation(summary = "Delete a patient", description = "Removes a patient from the system by ID")
    @ApiResponse(responseCode = "204", description = "Patient deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
