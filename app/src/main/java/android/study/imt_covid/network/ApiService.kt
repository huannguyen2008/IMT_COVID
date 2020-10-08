package android.study.imt_covid.network

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import android.study.imt_covid.network.interceptor.ConnectivityInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// https://imt-covid19.herokuapp.com/vietnam/api?key=summary
// https://imt-covid19.herokuapp.com/vietnam/api?key=city_summary
// https://imt-covid19.herokuapp.com/index/api?key=summary
// https://imt-covid19.herokuapp.com/index/api?key=country_summary
interface ApiService {
    @GET(value = "vietnam/api")
    suspend fun getVnSummaryData(
        @Query(value = "key") key: String = "summary")
            : VnSummaryResponse

    @GET(value = "index/api")
    suspend fun getWorldSummaryData(
        @Query(value = "key") key: String = "summary")
            : WorldSummaryResponse

    @GET(value = "index/api")
    suspend fun getCountrySummaryData(
        @Query(value = "key") key: String = "country_summary")
            : CountrySummaryResponse

    @GET(value = "vietnam/api")
    suspend fun getVnCityData(
        @Query(value = "key") key: String = "city_summary")
            : VnCityResponse

    @GET(value = "vietnam/api")
    suspend fun getVnNationalityData(
        @Query(value = "key") key: String = "nationality")
            : VnNationalityResponse

    @GET(value = "vietnam/api")
    suspend fun getVnGenderData(
        @Query(value = "key") key: String = "gender")
            : VnGenderResponse

    @GET(value = "vietnam/api")
    suspend fun getVnAgeData(
        @Query(value = "key") key: String = "age")
            : VnAgeResponse

    @GET(value = "last_update")
    suspend fun getLastUpdateData(
        @Query(value = "last_update") LastUpdate: LastUpdate)
            : LastUpdateResponse

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val gson = GsonBuilder()
                .registerTypeAdapter(VnSummary::class.java, VnSummaryTypeConverter())
                .registerTypeAdapter(VnCity::class.java, VnCityTypeConverter())
                .registerTypeAdapter(VnNationality::class.java, VnNationalityTypeConverter())
                .registerTypeAdapter(VnGender::class.java, VnGenderTypeConverter())
                .registerTypeAdapter(VnAge::class.java, VnAgeTypeConverter())
                .registerTypeAdapter(WorldSummary::class.java, WorldSummaryTypeConverter())
                .registerTypeAdapter(CountrySummary::class.java, CountrySummaryTypeConverter())

                .create()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://imt-covid19.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }

    }
}