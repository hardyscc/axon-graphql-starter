package com.example.ags.config;

import com.example.ags.aggregate.HospitalAggregate;
import com.example.ags.aggregate.WardAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public EventSourcingRepository<HospitalAggregate> repositoryForHospital(EventStore eventStore) {
        return EventSourcingRepository.builder(HospitalAggregate.class).eventStore(eventStore).build();
    }

    @Bean
    public EventSourcingRepository<WardAggregate> repositoryForWard(EventStore eventStore) {
        return EventSourcingRepository.builder(WardAggregate.class).eventStore(eventStore).build();
    }
}