package android.study.imt_covid.viewmodel

import android.study.imt_covid.data.repository.VnSummaryRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val VnSummaryRepository: VnSummaryRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(VnSummaryRepository) as T
    }
}