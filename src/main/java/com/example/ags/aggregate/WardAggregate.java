package com.example.ags.aggregate;

import com.example.ags.api.WardCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@NoArgsConstructor
public class WardAggregate {

    @AggregateIdentifier
    private String id;

    private String wardCode;

    public WardAggregate(String hospCode, String wardCode) {
        AggregateLifecycle.apply(new WardCreatedEvent(hospCode, wardCode));
    }

    @EventSourcingHandler
    private void on(WardCreatedEvent evt) {
        log.info("Received {}", evt);
        this.id = evt.getHospCode() + ":" + evt.getWardCode();
    }
}
