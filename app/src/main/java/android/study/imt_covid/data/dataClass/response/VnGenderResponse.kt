package android.study.imt_covid.data.dataClass.response


import android.study.imt_covid.data.dataClass.entity.VnGender
import com.google.gson.annotations.SerializedName

data class VnGenderResponse(
    @SerializedName(value = "data")
    val VnGender: VnGender,
    val success: Boolean
)