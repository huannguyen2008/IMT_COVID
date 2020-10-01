package android.study.imt_covid.ui.viewmodel.factory

import android.study.imt_covid.repository.CovidRepository
import android.study.imt_covid.ui.viewmodel.ChartViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChartViewModelFactory(
    private val CovidRepository: CovidRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChartViewModel(CovidRepository) as T
    }
}