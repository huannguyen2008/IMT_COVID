package android.study.imt_covid.ui.viewmodel

import android.study.imt_covid.data.repository.CovidRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HealthViewModelFactory(
    private val CovidRepository: CovidRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HealthViewModel(CovidRepository) as T
    }
}