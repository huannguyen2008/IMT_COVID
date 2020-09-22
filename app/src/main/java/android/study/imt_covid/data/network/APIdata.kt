package android.study.imt_covid.data.network

import android.study.imt_covid.data.dataClass.*
import android.study.imt_covid.data.network.Interceptor.ConnectivityInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


// https://imt-covid19.herokuapp.com/vietnam/api?key=summary
// https://imt-covid19.herokuapp.com/vietnam/api?key=city_summary
interface APIdata {

    @GET(value = "api")
    fun getVnSummaryData(@Query(value = "summary") VnSummary: VnSummary)
            : Deferred<VnSummaryResponse>
    @GET(value = "api")
    fun getVnCityData(@Query(value = "city_summary") VnCity: List<VnCity>)
            :Deferred<VnCityResponse>
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): APIdata {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", "summary")
                    .addQueryParameter("key", "city_summary")
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
                .build()
            val gson = GsonBuilder()
                .registerTypeAdapter(VnSummary::class.java, VnSummaryTypeConverter())
                .registerTypeAdapter(VnCity::class.java,VnCityTypeConverter())
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://imt-covid19.herokuapp.com/vietnam/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIdata::class.java)
        }

    }
}