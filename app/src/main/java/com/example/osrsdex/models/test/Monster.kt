package com.example.osrsdex.models.test

import android.os.Parcelable
import com.example.osrsdex.models.CombatStats
import kotlinx.parcelize.Parcelize

@Parcelize
data class Monster (
    override var entityName: String,
    override var stats: CombatStats?,
    override var atkStyle: String?,

) : IEntity, Parcelable