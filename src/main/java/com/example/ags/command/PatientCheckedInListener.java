package com.example.ags.command;

import com.example.ags.api.PatientCheckedInEvent;
import com.example.ags.api.UpdateWardInfoCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PatientCheckedInListener {

    private final CommandGateway commandGateway;

    @EventHandler
    public void on(PatientCheckedInEvent evt) {
        log.info("PatientCheckedInListener {}", evt);
        String[] wardIdArr = evt.getWardId().split(":");

        this.commandGateway.send(new UpdateWardInfoCommand(evt.getHkid(), wardIdArr[0], wardIdArr[1], evt.getBedNum()));
    }
}
