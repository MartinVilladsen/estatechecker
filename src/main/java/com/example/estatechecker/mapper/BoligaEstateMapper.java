package com.example.estatechecker.mapper;

import com.example.estatechecker.model.dto.BoligaEstateDto;
import com.example.estatechecker.model.entity.BoligaEstate;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.MappingException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@UtilityClass
public class BoligaEstateMapper {

    public @Nullable BoligaEstate toEntity(BoligaEstateDto dto) {
        try {
            if (dto == null) {
                return null;
            }

            if (dto.estateCode() == null) {
                throw new MappingException("Estate code is null");
            }

            // TODO: Make checks for notnullable properties and throw exceptions. ALso add
            // tests for this later

            BoligaEstate estate = new BoligaEstate();

            estate.setId(toUuid(dto.guid()));

            estate.setAddress(dto.address());
            estate.setZipCode(dto.zipCode());
            estate.setPrice(dto.price());
            estate.setSoldDate(dto.soldDate());
            estate.setPropertyType(dto.propertyType());
            estate.setSaleType(dto.saleType());
            estate.setSqmPrice(dto.sqmPrice());
            estate.setRooms(dto.rooms());
            estate.setSize(dto.size());
            estate.setBuildYear(dto.buildYear());
            estate.setChange(dto.change());
            estate.setGuid(dto.guid());
            estate.setLatitude(dto.latitude());
            estate.setLongitude(dto.longitude());
            estate.setMunicipalityCode(dto.municipalityCode());
            estate.setEstateCode(dto.estateCode());
            estate.setCity(dto.city());
            estate.setGroupKey(dto.groupKey());
            estate.setCanGetVR(dto.canGetVR());
            estate.setBfEnr(dto.bfEnr());
            estate.setOuId(dto.ouId());
            estate.setOuAddress(dto.ouAddress());

            return estate;
        } catch (MappingException e) {
            throw new MappingException("DTO was null");
        }
    }

    public UUID toUuid(String guid) {
        if (guid == null) {
            return UUID.randomUUID();
        }

        try {
            return UUID.fromString(guid);
        } catch (IllegalArgumentException ex) {
            return UUID.nameUUIDFromBytes(guid.getBytes());
        }
    }
}
