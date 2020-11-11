package com.example.ags.api

data class CreateHospitalCommand(var hospCode: String)
data class CreateWardCommand(var hospCode: String, var wardCode: String)