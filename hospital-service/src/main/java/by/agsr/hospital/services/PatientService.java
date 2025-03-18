package by.agsr.hospital.services;

import by.agsr.hospital.dto.PatientDto;
import by.agsr.hospital.entities.Patient;
import by.agsr.hospital.mappers.PatientMapper;
import by.agsr.hospital.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Transactional(readOnly = true)
    public PatientDto findById(UUID id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Patient with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<PatientDto> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PatientDto create(PatientDto patientDto) {
        Patient patient = patientMapper.toEntity(patientDto);
        patient.setId(null);
        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Transactional
    public void deleteById(UUID id) {
        patientRepository.findById(id)
                .ifPresentOrElse(
                        patientRepository::delete,
                        () -> { throw new EntityNotFoundException("Patient with " + id + " not found"); }
                );
    }

    @Transactional
    public PatientDto update(UUID id, PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient with " + id + " not found"));


        if (patientDto.getName() != null) {
            existingPatient.setName(patientDto.getName());
        }
        if (patientDto.getGender() != null) {
            existingPatient.setGender(patientDto.getGender());
        }
        if (patientDto.getBirthDate() != null) {
            existingPatient.setBirthDate(patientDto.getBirthDate());
        }

        return patientMapper.toDTO(patientRepository.save(existingPatient));
    }
}
