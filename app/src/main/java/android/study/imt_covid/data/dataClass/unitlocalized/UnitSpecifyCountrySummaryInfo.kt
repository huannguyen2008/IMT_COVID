package android.study.imt_covid.data.dataClass.unitlocalized
// ['Country_Region','Confirmed', 'Deaths', 'Active', 'Recovered', 'new_cases', 'new_deaths']

interface UnitSpecifyCountrySummaryInfo {
    val countryRegion: String
    val total: Int
    val death: Int
    val active: Int
    val recovered: Int
    val newCases: Int
    val newDeath: Int
}