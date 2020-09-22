package android.study.imt_covid.data.network.networkSource

import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import androidx.lifecycle.LiveData

interface VnSummarySource {
    val downloadedVnSummary: LiveData<VnSummaryResponse>

    suspend fun fetchVnSummary(
        VnSummary: VnSummary
    )
}