package com.example.ags.query.ward;

import com.example.ags.api.FindWardQuery;
import com.example.ags.api.WardCreatedEvent;
import com.example.ags.query.WardViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class WardProjector {

    private final WardViewRepository repository;

    @EventHandler
    public void on(WardCreatedEvent evt) {
        log.info("WardProjector: {}", evt);
        this.repository.save(new WardView(evt.getHospCode() + ":" + evt.getWardCode(), evt.getWardCode()));
    }

    @QueryHandler
    public WardView handle(FindWardQuery query) {
        return this.repository.findById(query.getHospCode() + ":" + query.getWardCode()).orElseThrow();
    }
}
