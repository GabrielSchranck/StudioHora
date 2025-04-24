package com.example.schedule.model.entities

import java.io.Serializable

class Schedule(
    var Cliente: String,
    var DataMarcada: String,
    var Horario: String,
    var Valor: Double,
    var Descricao: String? = null
): Serializable
