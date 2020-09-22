package android.study.imt_covid.data.unitlocalized

interface UnitSpecifyVnSummaryInfo {
    val diff: Int
    val recover: Int
    val totalDeath: Int
    val active: Int
    val total: Int
    val newCases: Int
    val newDeath: Int
}