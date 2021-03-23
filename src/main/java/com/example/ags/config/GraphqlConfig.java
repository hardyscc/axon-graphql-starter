package com.example.ags.config;

import com.example.ags.resolver.HospitalResolver;
import com.example.ags.resolver.PatientResolver;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GraphqlConfig {

    private final HospitalResolver hospitalResolver;
    private final PatientResolver patientResolver;

    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withBasePackages("org.ha.spl")
                .withOperationsFromSingleton(this.hospitalResolver)
                .withOperationsFromSingleton(this.patientResolver)
                .generate();
    }
}