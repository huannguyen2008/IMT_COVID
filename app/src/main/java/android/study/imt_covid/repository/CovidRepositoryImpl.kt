package android.study.imt_covid.repository

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import android.study.imt_covid.network.networkSource.DataSource
import android.study.imt_covid.data.dtbAndDAO.*
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class CovidRepositoryImpl(
    val VnSummaryDAO: VnSummaryDAO,
    private val VnCityDAO: VnCityDAO,
    private val VnNationalityDAO: VnNationalityDAO,
    private val VnGenderDAO: VnGenderDAO,
    private val VnAgeDAO: VnAgeDAO,
    private val WorldSummaryDAO: WorldSummaryDAO,
    private val LastUpdateDAO: LastUpdateDAO,
    private val CountrySummaryDAO: CountrySummaryDAO,
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
            downloadedWorldSummary.observeForever { newWorldSummary ->
                persistFetchedWorldSummary(newWorldSummary)
            }
            downloadedLastUpdate.observeForever { newLastUpdate ->
                persistFetchedLastUpdate(newLastUpdate)
            }
            downloadedCountrySummary.observeForever { newCountrySummary ->
                persistFetchedCountrySummary(newCountrySummary)
            }
        }
    }

    private suspend fun initData() {
//        if (isFetchNeeded(ZonedDateTime.now().minusHours(1)))
        fetchVnSummary()
        fetchWorldSummary()
        fetchVnCity()
        fetchVnNationality()
        fetchVnGender()
        fetchVnAge()
        fetchLastUpdate()
        fetchCountrySummary()
    }

    // get data
    override suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out VnSummary> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext VnSummaryDAO.getVnSummaryData()
        }
    }

    override suspend fun getWorldSummary(WorldSummary: WorldSummary): LiveData<out WorldSummary> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext WorldSummaryDAO.getWorldSummaryData()
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

    override suspend fun getLastUpdate(LastUpdate: LastUpdate): LiveData<out LastUpdate> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext LastUpdateDAO.getLastUpdateData()
        }
    }

    override suspend fun getCountrySummary(CountrySummary: List<CountrySummary>): LiveData<out List<CountrySummary>> {
        return withContext(Dispatchers.IO) {
            initData()
            return@withContext CountrySummaryDAO.getCountrySummaryData()
        }
    }

    // persist fetched data
    private fun persistFetchedVnSummary(fetchedVnSummary: VnSummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            VnSummaryDAO.upsert(fetchedVnSummary.VnSummary)
        }
    }

    private fun persistFetchedWorldSummary(fetchedWorldSummary: WorldSummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            WorldSummaryDAO.upsert(fetchedWorldSummary.WorldSummary)
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

    private fun persistFetchedLastUpdate(fetchedLastUpdate: LastUpdateResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            LastUpdateDAO.upsert(LastUpdate(fetchedLastUpdate.LastUpdate))
        }
    }

    private fun persistFetchedCountrySummary(fetchedCountrySummary: CountrySummaryResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            CountrySummaryDAO.upsert(fetchedCountrySummary.CountrySummary)
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

    private suspend fun fetchWorldSummary() {
        val diff = 0
        val recover = 0
        val totalDeath = 0
        val active = 0
        val total = 0
        val newCases = 0
        val newDeath = 0
        DataSource.fetchWorldSummary(
            WorldSummary = WorldSummary(
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

    private suspend fun fetchCountrySummary() {
        val countryRegion = "nothing"
        val total = 0
        val death = 0
        val active = 0
        val recovered = 0
        val newCases = 0
        val newDeath = 0
        val listCountrySummary =
            listOf(CountrySummary(countryRegion, total, death, active, recovered, newCases, newDeath))
        DataSource.fetchCountrySummary(listCountrySummary)
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

    private suspend fun fetchLastUpdate() {
        val lastUpdate = "nothing"
        DataSource.fetchLastUpdate(LastUpdate(lastUpdate))
    }

    private fun isFetchNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinAgo = ZonedDateTime.now().minusMinutes(1000)
        return lastFetchTime.isBefore(thirtyMinAgo)
    }

}