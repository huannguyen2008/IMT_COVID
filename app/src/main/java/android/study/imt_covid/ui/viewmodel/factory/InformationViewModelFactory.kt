package android.study.imt_covid.ui.viewmodel.factory

import android.study.imt_covid.data.repository.CovidRepository
import android.study.imt_covid.ui.viewmodel.InformationViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InformationViewModelFactory(
    private val CovidRepository: CovidRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InformationViewModel(CovidRepository) as T
    }
}