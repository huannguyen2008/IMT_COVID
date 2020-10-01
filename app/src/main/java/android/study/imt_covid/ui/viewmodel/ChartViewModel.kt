package android.study.imt_covid.ui.viewmodel

import android.study.imt_covid.data.dataClass.entity.VnAge
import android.study.imt_covid.data.dataClass.entity.VnGender
import android.study.imt_covid.data.dataClass.entity.VnNationality
import android.study.imt_covid.repository.CovidRepository
import android.study.imt_covid.internal.lazyDeferred
import androidx.lifecycle.ViewModel

class ChartViewModel(
    val CovidRepository: CovidRepository

) : ViewModel() {
    val vnNation by lazyDeferred {
        val nationality = "nothing"
        val numberCases = 0

        CovidRepository.getVnNationality(
            listOf(
                VnNationality(
                    nationality, numberCases
                )
            )
        )
    }
    val vnGender by lazyDeferred {
        val male = 0
        val female = 0

        CovidRepository.getVnGender(VnGender(male, female))
    }
    val vnAge by lazyDeferred {
        val patient = "nothing"
        val age = 0

        CovidRepository.getVnAge(
            listOf(
                VnAge(patient, age
                )
            )
        )
    }
}