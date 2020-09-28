package android.study.imt_covid.data.network

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import android.study.imt_covid.data.network.Interceptor.ConnectivityInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
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
interface APIdata {
    @GET(value = "vietnam/api")
    fun getVnSummaryData(@Query(value = "key") key: String = "summary")
            : Deferred<VnSummaryResponse>

    @GET(value = "index/api")
    fun getWorldSummaryData(@Query(value = "key") key: String = "summary")
            : Deferred<WorldSummaryResponse>

    @GET(value = "vietnam/api")
    fun getVnCityData(@Query(value = "key") key: String = "city_summary")
            : Deferred<VnCityResponse>

    @GET(value = "vietnam/api")
    fun getVnNationalityData(@Query(value = "key") key: String = "nationality")
            : Deferred<VnNationalityResponse>

    @GET(value = "vietnam/api")
    fun getVnGenderData(@Query(value = "key") key: String = "gender")
            : Deferred<VnGenderResponse>

    @GET(value = "vietnam/api")
    fun getVnAgeData(@Query(value = "key") key: String = "age")
            : Deferred<VnAgeResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): APIdata {
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
                .create()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://imt-covid19.herokuapp.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIdata::class.java)
        }

    }
}