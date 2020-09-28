package android.study.imt_covid.data.dataClass.response

import android.study.imt_covid.data.dataClass.entity.WorldSummary
import com.google.gson.annotations.SerializedName

data class WorldSummaryResponse(
    @SerializedName(value = "data")
    val WorldSummary: WorldSummary,
    val success: Boolean
)