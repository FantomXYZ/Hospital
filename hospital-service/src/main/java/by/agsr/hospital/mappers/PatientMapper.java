package by.agsr.hospital.mappers;

import by.agsr.hospital.dto.PatientDto;
import by.agsr.hospital.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(target = "id", source = "id")
    PatientDto toDTO(Patient patient);

    @Mapping(target = "id", source = "id")
    Patient toEntity(PatientDto patientDto);
}
