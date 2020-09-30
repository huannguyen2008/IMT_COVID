package android.study.imt_covid.data.dataClass.response


import android.study.imt_covid.data.dataClass.entity.CountrySummary
import com.google.gson.annotations.SerializedName

data class CountrySummaryResponse(
    @SerializedName(value = "data")
    val CountrySummary: List<CountrySummary>,
    val success: Boolean
)