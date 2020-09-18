package android.study.imt_covid.data.dataClass


import android.study.imt_covid.data.dataClass.CaseInfo
import com.google.gson.annotations.SerializedName

data class TotalAndActiveResponse(
    @SerializedName(value = "data")
    val TotalAndActive: List<CaseInfo>,
    val success: Boolean
)

