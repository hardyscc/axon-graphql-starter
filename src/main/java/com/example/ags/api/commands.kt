package com.example.ags.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateHospitalCommand(@TargetAggregateIdentifier var hospCode: String)
data class AddWardCommand(@TargetAggregateIdentifier var hospCode: String, var wardCode: String)