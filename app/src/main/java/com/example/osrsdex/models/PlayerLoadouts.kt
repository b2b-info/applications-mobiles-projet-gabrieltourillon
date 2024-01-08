package com.example.osrsdex.models

import androidx.room.Embedded
import androidx.room.Relation

data class PlayerLoadouts
    (
            @Embedded val player: Player,
            @Relation(
                parentColumn = "playerName",
                entityColumn = "loadoutPlayerName"
            )
            val loadouts: List<Loadout>
)