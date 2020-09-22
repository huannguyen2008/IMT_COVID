package android.study.imt_covid.data.network.networkSource

import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.internal.NoConnectivityException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class VnSummarySourceImpl(
    private val APIdata: APIdata
) : VnSummarySource {
    private val _downloadedVnSummary = MutableLiveData<VnSummaryResponse>()
    override val downloadedVnSummary: LiveData<VnSummaryResponse>
        get() = _downloadedVnSummary


    override suspend fun fetchVnSummary(VnSummary: VnSummary) {
        try {
            val fetchedVnSummary = APIdata
                .getCurrentData(VnSummary)
                .await()
            _downloadedVnSummary.postValue(fetchedVnSummary)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
}