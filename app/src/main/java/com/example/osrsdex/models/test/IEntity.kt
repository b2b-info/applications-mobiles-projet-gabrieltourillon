package com.example.osrsdex.models.test

import com.example.osrsdex.models.CombatStats

interface IEntity {
    var entityName: String
    var stats: CombatStats?
    var atkStyle: String?
}