package android.study.imt_covid.data.dataClass.unitlocalized

interface UnitSpecifySummaryInfo {
    val diff: Int
    val recover: Int
    val totalDeath: Int
    val active: Int
    val total: Int
    val newCases: Int
    val newDeath: Int
}