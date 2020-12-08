package com.example.ags.query

import com.example.ags.query.dashboard.Hospital
import com.example.ags.query.dashboard.Ward
import com.example.ags.query.hospital.HospitalView
import com.example.ags.query.ward.WardView
import org.springframework.data.jpa.repository.JpaRepository

interface HospitalViewRepository : JpaRepository<HospitalView, String>
interface WardViewRepository : JpaRepository<WardView, String>

interface HospitalRepository : JpaRepository<Hospital, String>
interface WardRepository : JpaRepository<Ward, String>