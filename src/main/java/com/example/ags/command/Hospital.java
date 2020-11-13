package com.example.ags.command;

import com.example.ags.api.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
@Slf4j
@NoArgsConstructor
public class Hospital {

    private final List<String> wards = new ArrayList<>();
    @AggregateIdentifier
    private String hospCode;

    @CommandHandler
    public Hospital(CreateHospitalCommand cmd) {
        log.info("Received {}", cmd);
        AggregateLifecycle.apply(new HospitalCreatedEvent(cmd.getHospCode()));
    }

    @CommandHandler
    public void on(AddWardCommand cmd) throws Exception {
        log.info("Received {}", cmd);
        if (this.wards.contains(cmd.getWardCode())) {
            throw new AddWardException();
        }
        AggregateLifecycle.createNew(Ward.class, () -> new Ward(cmd.getHospCode(), cmd.getWardCode()));
        AggregateLifecycle.apply(new WardAddedEvent(cmd.getHospCode(), cmd.getWardCode()));
    }

    @EventSourcingHandler
    public void on(HospitalCreatedEvent evt) {
        log.info("Received {}", evt);
        this.hospCode = evt.getHospCode();
    }

    @EventSourcingHandler
    public void on(WardAddedEvent evt) {
        log.info("Received {}", evt);
        this.wards.add(evt.getWardCode());
    }
}
