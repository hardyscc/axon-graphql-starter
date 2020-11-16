package com.example.ags.config;

import com.example.ags.aggregate.HospitalAggregate;
import com.example.ags.aggregate.WardAggregate;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public Repository<HospitalAggregate> hospitalAggregateRepository(EventStore eventStore, Cache hospitalAggregateCache) {
        return EventSourcingRepository.builder(HospitalAggregate.class)
                .cache(hospitalAggregateCache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Repository<WardAggregate> wardAggregateRepository(EventStore eventStore, Cache wardAggregateCache) {
        return EventSourcingRepository.builder(WardAggregate.class)
                .cache(wardAggregateCache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache hospitalAggregateCache() {
        return new WeakReferenceCache();
    }

    @Bean
    public Cache wardAggregateCache() {
        return new WeakReferenceCache();
    }

}
