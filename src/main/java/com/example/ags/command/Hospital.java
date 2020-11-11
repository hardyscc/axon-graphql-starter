package com.example.ags.command;

import com.example.ags.api.CreateHospitalCommand;
import com.example.ags.api.HospitalCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@NoArgsConstructor
public class Hospital {

    @AggregateIdentifier
    private String hospCode;

    @CommandHandler
    public Hospital(CreateHospitalCommand cmd) {
        AggregateLifecycle.apply(new HospitalCreatedEvent(cmd.getHospCode()));
    }

    @EventSourcingHandler
    public void on(HospitalCreatedEvent evt) {
        log.info("Received a HospitalCreatedEvent for {}", evt.getHospCode());
        this.hospCode = evt.getHospCode();
    }
}
