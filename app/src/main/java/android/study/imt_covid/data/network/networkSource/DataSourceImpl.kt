package android.study.imt_covid.data.network.networkSource

import android.study.imt_covid.data.dataClass.entity.VnCity
import android.study.imt_covid.data.dataClass.VnCityResponse
import android.study.imt_covid.data.dataClass.entity.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.internal.NoConnectivityException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSourceImpl(
    private val APIdata: APIdata
) : DataSource {

    // get VN summary
    private val _downloadedVnSummary = MutableLiveData<VnSummaryResponse>()
    override val downloadedVnSummary: LiveData<VnSummaryResponse>
        get() = _downloadedVnSummary


    override suspend fun fetchVnSummary(VnSummary: VnSummary) {
        try {
            val fetchedVnSummary = APIdata
                .getVnSummaryData()
                .await()
            _downloadedVnSummary.postValue(fetchedVnSummary)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }

    // get city VN summary
    private val _downloadedVnCity = MutableLiveData<VnCityResponse>()
    override val downloadedVnCity: LiveData<VnCityResponse>
        get() = _downloadedVnCity


    override suspend fun fetchVnCity(VnCity: List<VnCity>) {
        try {
            val fetchedVnCity = APIdata
                .getVnCityData()
                .await()
            _downloadedVnCity.postValue(fetchedVnCity)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
}