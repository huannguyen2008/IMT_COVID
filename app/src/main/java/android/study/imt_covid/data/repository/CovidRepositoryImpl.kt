package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnGenderInfo
import android.study.imt_covid.data.network.networkSource.DataSource
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
import android.study.imt_covid.data.dtbAndDAO.*
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class CovidRepositoryImpl(
    private val VnSummaryDAO: VnSummaryDAO,
    private val VnCityDAO: VnCityDAO,
    private val VnNationalityDAO: VnNationalityDAO,
    private val VnGenderDAO: VnGenderDAO,
    private val VnAgeDAO: VnAgeDAO,

    private val DataSource: DataSource
) : CovidRepository {

    init {
        DataSource.apply {
            downloadedVnSummary.observeForever { newVnSummary ->
                persistFetchedVnSummary(newVnSummary)
            }
            downloadedVnCity.observeForever { newVnCity ->
                persistFetchedVnCity(newVnCity)
            }
            downloadedVnNationality.observeForever { newVnNationality ->
                persistFetchedVnNationality(newVnNationality)
            }
            downloadedVnGender.observeForever { newVnGender ->
                persistFetchedVnGender(newVnGender)
            }
            downloadedVnAge.observeForever { newVnAge ->
                persistFetchedVnAge(newVnAge)
            }
        }
    }

    override suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out VnSummary> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnSummaryDAO.getVnSummaryData()
        }
    }

    override suspend fun getVnCity(VnCity: List<VnCity>): LiveData<out List<VnCity>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnCityDAO.getVnCityData()
        }
    }

    override suspend fun getVnNationality(VnNationality: List<VnNationality>): LiveData<out List<VnNationality>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnNationalityDAO.getVnNationalityData()
        }
    }

    override suspend fun getVnGender(VnGender: VnGender): LiveData<out VnGender> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnGenderDAO.getVnGenderData()
        }
    }

    override suspend fun getVnAge(VnAge: List<VnAge>): LiveData<out List<VnAge>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnAgeDAO.getVnAgeData()
        }
    }

    private fun persistFetchedVnSummary(fetchedVnSummary: VnSummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnSummaryDAO.upsert(fetchedVnSummary.VnSummary)
        }
    }

    private fun persistFetchedVnCity(fetchedVnCity: VnCityResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnCityDAO.upsert(fetchedVnCity.VnCity)
        }
    }

    private fun persistFetchedVnNationality(fetchedVnNationality: VnNationalityResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnNationalityDAO.upsert(fetchedVnNationality.VnNationality)
        }
    }

    private fun persistFetchedVnGender(fetchedVnGender: VnGenderResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnGenderDAO.upsert(fetchedVnGender.VnGender)
        }
    }

    private fun persistFetchedVnAge(fetchedVnAge: VnAgeResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnAgeDAO.upsert(fetchedVnAge.VnAge)
        }
    }

    private suspend fun initData() {
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnSummary()
        }
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnCity()
        }
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnNationality()
        }
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnGender()
        }
        if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchVnAge()
        }
    }

    private suspend fun fetchVnSummary() {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        DataSource.fetchVnSummary(
            VnSummary = VnSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
        )
    }


    private suspend fun fetchVnCity() {
        val city = "nothing"
        val totalCity = 0
        val activeCity = 0
        val recoveredCity = 0
        val deathCity = 0
        val diffCity = 0
        val listCity =
            listOf(VnCity(city, totalCity, activeCity, recoveredCity, deathCity, diffCity))
        DataSource.fetchVnCity(listCity)
    }

    private suspend fun fetchVnNationality() {
        val national = "nothing"
        val numberCases = 0
        val listNationality = listOf(VnNationality(national, numberCases))
        DataSource.fetchVnNationality(listNationality)
    }

    private suspend fun fetchVnGender() {
        val male = 0
        val female = 0
        DataSource.fetchVnGender(
            VnGender = VnGender(male, female)
        )
    }

    private suspend fun fetchVnAge() {
        val patient = "nothing"
        val age = 0
        val listAge = listOf(VnAge(patient, age))
        DataSource.fetchVnAge(listAge)
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }

}