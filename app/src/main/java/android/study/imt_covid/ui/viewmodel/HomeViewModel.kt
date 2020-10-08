package android.study.imt_covid.viewmodel

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.repository.CovidRepository
import android.study.imt_covid.internal.lazyDeferred
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat


class HomeViewModel(
    val CovidRepository: CovidRepository
) : ViewModel() {
    val vnSummary by lazyDeferred {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        CovidRepository.getVnSummary(
            VnSummary(
                diff,
                recover,
                totalDeath,
                active,
                total,
                newCases,
                newDeath
            )
        )
    }
    val worldSummary by lazyDeferred {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        CovidRepository.getWorldSummary(
            WorldSummary(
                diff,
                recover,
                totalDeath,
                active,
                total,
                newCases,
                newDeath
            )
        )
    }
    val vnCity by lazyDeferred {
        val city = "nothing"
        val totalCity = 0
        val activeCity = 0
        val recoveredCity = 0
        val deathCity = 0
        val diffCity = 0
        CovidRepository.getVnCity(
            listOf(
                VnCity(
                    city, totalCity, activeCity, recoveredCity, deathCity, diffCity
                )
            )
        )
    }
    val countrySummary by lazyDeferred {
        val countryRegion = "nothing"
        val total = 0
        val death = 0
        val active = 0
        val recovered = 0
        val newCases = 0
        val newDeath = 0
        CovidRepository.getCountrySummary(
            listOf(
                CountrySummary(
                    countryRegion,
                    total,
                    death,
                    active,
                    recovered,
                    newCases,
                    newDeath
                )
            )
        )
    }
    val lastUpdate by lazyDeferred {
        val lastUpdate = "nothing"
        CovidRepository.getLastUpdate(LastUpdate(lastUpdate))
    }

}