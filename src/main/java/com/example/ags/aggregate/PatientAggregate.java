package com.example.ags.aggregate;

import com.example.ags.api.CreatePatientCommand;
import com.example.ags.api.PatientCreatedEvent;
import com.example.ags.api.UpdateWardInfoCommand;
import com.example.ags.api.WardInfoUpdatedEvent;
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
public class PatientAggregate {

    @AggregateIdentifier
    private String hkid;
    private String name;

    private String hospCode;
    private String wardCode;
    private Integer bedNum;

    @CommandHandler
    public PatientAggregate(CreatePatientCommand cmd) {
        AggregateLifecycle.apply(new PatientCreatedEvent(cmd.getHkid(), cmd.getName()));
    }

    @CommandHandler
    public void on(UpdateWardInfoCommand cmd) {
        AggregateLifecycle.apply(new WardInfoUpdatedEvent(cmd.getHkid(), cmd.getHospCode(), cmd.getWardCode(), cmd.getBedNum()));
    }

    @EventSourcingHandler
    private void on(PatientCreatedEvent evt) {
        log.info("Received {}", evt);
        this.hkid = evt.getHkid();
        this.name = evt.getName();
    }

    @EventSourcingHandler
    private void on(WardInfoUpdatedEvent evt) {
        log.info("Received {}", evt);
        this.hospCode = evt.getHospCode();
        this.wardCode = evt.getWardCode();
        this.bedNum = evt.getBedNum();
    }
}
