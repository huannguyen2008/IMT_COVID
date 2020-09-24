package android.study.imt_covid.data.dataClass.response


import android.study.imt_covid.data.dataClass.entity.VnSummary
import com.google.gson.annotations.SerializedName

data class VnSummaryResponse(
    @SerializedName(value = "data")
    val VnSummary: VnSummary,
    val success: Boolean
)