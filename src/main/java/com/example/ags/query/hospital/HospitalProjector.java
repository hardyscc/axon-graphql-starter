package com.example.ags.query.hospital;

import com.example.ags.api.FindHospitalQuery;
import com.example.ags.api.HospitalCreatedEvent;
import com.example.ags.api.ListHospitalQuery;
import com.example.ags.api.WardAddedEvent;
import com.example.ags.query.HospitalViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class HospitalProjector {

    private final HospitalViewRepository repository;

    @EventHandler
    public void on(HospitalCreatedEvent evt) {
        log.info("HospitalProjector: {}", evt);
        this.repository.save(new HospitalView(evt.getHospCode(), new ArrayList<>()));
    }

    @EventHandler
    public void on(WardAddedEvent evt) {
        log.info("HospitalProjector: {}", evt);
        HospitalView hospitalView = this.repository.findById(evt.getHospCode()).orElseThrow();
        hospitalView.getWards().add(evt.getWardCode());
    }

    @QueryHandler
    public List<HospitalView> handle(ListHospitalQuery query) {
        return this.repository.findAll();
    }

    @QueryHandler
    public HospitalView handle(FindHospitalQuery query) {
        return this.repository.findById(query.getHospCode()).orElse(null);
    }
}
