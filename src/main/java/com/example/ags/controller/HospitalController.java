package com.example.ags.controller;

import com.example.ags.api.*;
import com.example.ags.query.HospitalView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@Slf4j
@RequestMapping(value = "/hospital")
@AllArgsConstructor
public class HospitalController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<Object> createHospital(@RequestBody CreateHospitalDTO dto) {
        log.info("createHospital {}", dto);
        return this.commandGateway.send(new CreateHospitalCommand(dto.getHospCode())).exceptionally(exception -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists");
        });
    }

    @PostMapping("/{hospCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<Object> addWard(@PathVariable("hospCode") String hospCode, @RequestBody AddWardDTO dto) {
        log.info("addWard {}", dto);
        return this.commandGateway.send(new AddWardCommand(hospCode, dto.getWardCode())).exceptionally(exception -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.toString());
        });
    }

    @GetMapping("/{hospCode}")
    public CompletableFuture<HospitalView> getHospital(@PathVariable("hospCode") String hospCode) {
        log.info("getHospital {}", hospCode);
        return this.queryGateway.query(new FindHospitalQuery(hospCode), ResponseTypes.instanceOf(HospitalView.class));
    }

    @GetMapping
    public CompletableFuture<List<HospitalView>> listHospital() {
        log.info("listHospital");
        return this.queryGateway.query(new ListHospitalQuery(), ResponseTypes.multipleInstancesOf(HospitalView.class));
    }
}
