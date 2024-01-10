package com.example.osrsdex.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(primaryKeys = ["loadoutName", "loadoutPlayerName"], foreignKeys = [
            ForeignKey(
                entity = Player::class,
                parentColumns = ["playerName"],
                childColumns = ["loadoutPlayerName"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE,
                deferred = true)])
data class Loadout(
    var loadoutName: String,
    var loadoutPlayerName: String,
    var description: String?,
    @Embedded var combatBonuses: CombatBonuses?
) : Parcelable
{
    @IgnoredOnParcel
    @Ignore var atkStyle: String? = null
}