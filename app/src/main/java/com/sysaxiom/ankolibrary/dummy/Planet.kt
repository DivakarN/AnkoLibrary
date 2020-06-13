@file:JvmName("PlanetUtils")

package com.sysaxiom.ankolibrary.dummy

import java.util.*

const val TERRESTRIAL = "Terrestrial"
const val GAS_GIANT = "Gas Giant"

val Planet.composition: String
    get() = if (inner) TERRESTRIAL else GAS_GIANT

data class Planet(
        val id: String = UUID.randomUUID().toString(),
        val name: String = "",
        val inner: Boolean = false,
        val description: String = "",
        val knownMoons: Int = 0,
        val imageResourceId: Int = -1,
        val orbitalPeriod: Float = 0F
) {
    override fun toString(): String = name
}