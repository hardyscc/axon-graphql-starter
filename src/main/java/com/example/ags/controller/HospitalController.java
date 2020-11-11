package com.example.ags.controller;

import com.example.ags.api.CreateHospitalCommand;
import com.example.ags.api.CreateHospitalDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class HospitalController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/hospital")
    public void createHospital(@RequestBody CreateHospitalDTO dto) {
        this.commandGateway.send(new CreateHospitalCommand(dto.getHospCode()));
    }

    @GetMapping("/hospital/{hospCode}")
    public void retrieveHospital(@RequestBody CreateHospitalDTO dto) {
        //return this.queryGateway.query();
    }

}
