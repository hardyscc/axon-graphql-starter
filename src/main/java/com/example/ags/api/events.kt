package com.example.ags.api

data class HospitalCreatedEvent(var hospCode: String)
data class WardCreatedEvent(var hospCode: String, var wardCode: String)