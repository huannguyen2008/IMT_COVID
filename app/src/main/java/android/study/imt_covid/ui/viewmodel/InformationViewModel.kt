package android.study.imt_covid.ui.viewmodel

import android.study.imt_covid.data.dataClass.entity.VnAge
import android.study.imt_covid.data.dataClass.entity.VnGender
import android.study.imt_covid.data.dataClass.entity.VnNationality
import android.study.imt_covid.data.repository.CovidRepository
import android.study.imt_covid.internal.lazyDeferred
import androidx.lifecycle.ViewModel

class InformationViewModel(
    val CovidRepository: CovidRepository
) : ViewModel() {

}