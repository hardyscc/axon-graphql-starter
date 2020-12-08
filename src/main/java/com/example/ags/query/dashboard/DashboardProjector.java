package com.example.ags.query.dashboard;

import com.example.ags.api.FindHospitalQuery;
import com.example.ags.api.HospitalCreatedEvent;
import com.example.ags.api.HospitalDTO;
import com.example.ags.api.WardCreatedEvent;
import com.example.ags.query.HospitalRepository;
import com.example.ags.query.WardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class DashboardProjector {

    private final HospitalRepository hospitalRepository;
    private final WardRepository wardRepository;

    @EventHandler
    public void on(HospitalCreatedEvent evt) {
        log.info("DashboardProjector: {}", evt);
        this.hospitalRepository.save(new Hospital(evt.getHospCode(), new ArrayList<>()));
    }


    @EventHandler
    public void on(WardCreatedEvent evt) {
        log.info("DashboardProjector: {}", evt);
        Hospital hospital = this.hospitalRepository.findById(evt.getHospCode()).orElse(null);
        this.wardRepository.save(new Ward(evt.getHospCode() + ":" + evt.getWardCode(), hospital, evt.getWardCode()));
    }

    @QueryHandler
    public HospitalDTO handle(FindHospitalQuery query) {
        Hospital hospital = this.hospitalRepository.findById(query.getHospCode()).orElse(null);
        if (hospital == null) {
            throw new RuntimeException("Hospital not found");
        }
        List<String> wards = hospital.getWards().stream().map(Ward::getWardCode).collect(Collectors.toList());
        return new HospitalDTO(hospital.getHospCode(), wards);
    }
}
