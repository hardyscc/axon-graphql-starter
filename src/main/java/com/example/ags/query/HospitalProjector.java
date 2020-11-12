package com.example.ags.query;

import com.example.ags.api.FindHospitalQuery;
import com.example.ags.api.HospitalCreatedEvent;
import com.example.ags.api.ListHospitalQuery;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HospitalProjector {

    private final HospitalViewRepository repository;

    @EventHandler
    public void on(HospitalCreatedEvent evt) {
        this.repository.save(new HospitalView(evt.getHospCode()));
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
