package com.example.ags.config;

import com.example.ags.command.Hospital;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public EventSourcingRepository<Hospital> repositoryForHospital(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(Hospital.class).cache(cache).eventStore(eventStore).build();
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
}