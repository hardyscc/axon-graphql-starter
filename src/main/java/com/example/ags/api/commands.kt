package com.example.ags.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateHospitalCommand(@TargetAggregateIdentifier var hospCode: String)
data class CreateWardCommand(@TargetAggregateIdentifier var hospCode: String, var wardCode: String)