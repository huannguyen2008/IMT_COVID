package android.study.imt_covid.data.network

import android.study.imt_covid.data.dataClass.TotalAndActiveResponse
import android.study.imt_covid.internal.NoConnectivityException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CasesNetworkDataSourceImpl(
    private val APIdata: APIdata
) : CasesNetworkDataSource {

    private val _downloadedTotalAndActive = MutableLiveData<TotalAndActiveResponse>()
    override val downloadedTotalAndActive: LiveData<TotalAndActiveResponse>
        get() = _downloadedTotalAndActive

    override suspend fun fetchTotalAndActive(TotalAndActive: String) {
        try {
            val fetchedTotalAndActive = APIdata
                .getCurrentData(TotalAndActive)
                .await()
            _downloadedTotalAndActive.postValue(fetchedTotalAndActive)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
}