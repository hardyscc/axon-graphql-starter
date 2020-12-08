package com.example.ags.api

import lombok.NoArgsConstructor

@NoArgsConstructor
data class CreateHospitalDTO(var hospCode: String)

@NoArgsConstructor
data class AddWardDTO(var wardCode: String)

data class HospitalDTO(var hospCode: String, var wards: List<String>)