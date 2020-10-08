package android.study.imt_covid.network.networkSource

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import android.study.imt_covid.network.ApiService
import android.study.imt_covid.internal.NoConnectivityException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSourceImpl(
    private val ApiService: ApiService
) : DataSource {

    // get VN summary
    private val _downloadedVnSummary = MutableLiveData<VnSummaryResponse>()
    override val downloadedVnSummary: LiveData<VnSummaryResponse>
        get() = _downloadedVnSummary


    override suspend fun fetchVnSummary(VnSummary: VnSummary) {
        try {
            val fetchedVnSummary = ApiService
                .getVnSummaryData()
            _downloadedVnSummary.postValue(fetchedVnSummary)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get world summary
    private val _downloadedWorldSummary = MutableLiveData<WorldSummaryResponse>()
    override val downloadedWorldSummary: LiveData<WorldSummaryResponse>
        get() = _downloadedWorldSummary


    override suspend fun fetchWorldSummary(WorldSummary: WorldSummary) {
        try {
            val fetchedWorldSummary = ApiService
                .getWorldSummaryData()
            _downloadedWorldSummary.postValue(fetchedWorldSummary)
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
            val fetchedVnCity = ApiService
                .getVnCityData()
            _downloadedVnCity.postValue(fetchedVnCity)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get VN nationality
    private val _downloadedVnNationality = MutableLiveData<VnNationalityResponse>()
    override val downloadedVnNationality: LiveData<VnNationalityResponse>
        get() = _downloadedVnNationality


    override suspend fun fetchVnNationality(VnNationality: List<VnNationality>) {
        try {
            val fetchedVnNationality = ApiService
                .getVnNationalityData()
            _downloadedVnNationality.postValue(fetchedVnNationality)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get VN gender
    private val _downloadedVnGender = MutableLiveData<VnGenderResponse>()
    override val downloadedVnGender: LiveData<VnGenderResponse>
        get() = _downloadedVnGender


    override suspend fun fetchVnGender(VnGender: VnGender) {
        try {
            val fetchedVnGender = ApiService
                .getVnGenderData()
            _downloadedVnGender.postValue(fetchedVnGender)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get VN age
    private val _downloadedVnAge = MutableLiveData<VnAgeResponse>()
    override val downloadedVnAge: LiveData<VnAgeResponse>
        get() = _downloadedVnAge


    override suspend fun fetchVnAge(VnAge: List<VnAge>) {
        try {
            val fetchedVnAge = ApiService
                .getVnAgeData()
            _downloadedVnAge.postValue(fetchedVnAge)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get last update
    private val _downloadedLastUpdate = MutableLiveData<LastUpdateResponse>()
    override val downloadedLastUpdate: LiveData<LastUpdateResponse>
        get() = _downloadedLastUpdate


    override suspend fun fetchLastUpdate(LastUpdate: LastUpdate) {
        try {
            val fetchedLastUpdate = ApiService
                .getLastUpdateData(LastUpdate)
            _downloadedLastUpdate.postValue(fetchedLastUpdate)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
    // get country summary
    private val _downloadedCountrySummary = MutableLiveData<CountrySummaryResponse>()
    override val downloadedCountrySummary: LiveData<CountrySummaryResponse>
        get() = _downloadedCountrySummary


    override suspend fun fetchCountrySummary(CountrySummary: List<CountrySummary>) {
        try {
            val fetchedLastUpdate = ApiService
                .getCountrySummaryData()
            _downloadedCountrySummary.postValue(fetchedLastUpdate)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No internet connection!",e)
        }
    }
}