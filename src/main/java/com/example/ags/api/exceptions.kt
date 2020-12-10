package com.example.ags.api

// Hospital
class WardAlreadyExistException : Exception()

// Ward
class BedAlreadyExistException : Exception()
class BedOccupiedException : Exception()