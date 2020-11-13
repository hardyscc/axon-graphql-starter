package com.example.ags.config;

import com.example.ags.command.Hospital;
import com.example.ags.command.Ward;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public EventSourcingRepository<Hospital> repositoryForHospital(EventStore eventStore) {
        return EventSourcingRepository.builder(Hospital.class).eventStore(eventStore).build();
    }

    @Bean
    public EventSourcingRepository<Ward> repositoryForWard(EventStore eventStore) {
        return EventSourcingRepository.builder(Ward.class).eventStore(eventStore).build();
    }
}