package android.study.imt_covid.data.network

import android.study.imt_covid.data.dataClass.CaseInfo
import android.study.imt_covid.data.dataClass.TotalAndActiveResponse
import androidx.lifecycle.LiveData

interface CasesNetworkDataSource {
    val downloadedTotalAndActive: LiveData<TotalAndActiveResponse>

    suspend fun fetchTotalAndActive(
        TotalAndActive: String
    )
}