package android.study.imt_covid.data.dataClass.response


import android.study.imt_covid.data.dataClass.entity.VnAge
import com.google.gson.annotations.SerializedName

data class VnAgeResponse(
    @SerializedName(value = "data")
    val VnAge: List<VnAge>,
    val success: Boolean
)