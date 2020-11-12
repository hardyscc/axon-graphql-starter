package com.example.ags.query

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class HospitalView(
        @Id val hospCode: String
)

interface HospitalViewRepository : JpaRepository<HospitalView, String>