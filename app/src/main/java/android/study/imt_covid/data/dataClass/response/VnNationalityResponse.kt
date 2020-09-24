package android.study.imt_covid.data.dataClass.response


import android.study.imt_covid.data.dataClass.entity.VnNationality
import com.google.gson.annotations.SerializedName

data class VnNationalityResponse(
    @SerializedName(value = "data")
    val VnNationality: List<VnNationality>,
    val success: Boolean
)