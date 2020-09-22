package android.study.imt_covid.viewmodel

import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.repository.VnSummaryRepository
import android.study.imt_covid.internal.lazyDeferred
import androidx.lifecycle.ViewModel


class HomeViewModel(
    val VnSummaryRepository: VnSummaryRepository
) : ViewModel() {

    val vnSummary by lazyDeferred {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        VnSummaryRepository.getVnSummary(
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
}