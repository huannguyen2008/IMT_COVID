package android.study.imt_covid.data.dataClass


import com.google.gson.annotations.SerializedName

data class VnCityResponse(
    @SerializedName(value = "data")
    val VnCity: List<VnCity>,
    val success: Boolean
)