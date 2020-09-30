package android.study.imt_covid.data.dataClass.response


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class LastUpdateResponse(
    @SerializedName("last_update")
    val LastUpdate: String,
    val success: Boolean
)
