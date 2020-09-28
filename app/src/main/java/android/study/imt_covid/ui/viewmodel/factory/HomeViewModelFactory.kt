package android.study.imt_covid.ui.viewmodel.factory

import android.study.imt_covid.data.repository.CovidRepository
import android.study.imt_covid.viewmodel.HomeViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val CovidRepository: CovidRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(CovidRepository) as T
    }
}