package android.study.imt_covid.data.network

import android.study.imt_covid.data.dataClass.CaseInfo
import android.study.imt_covid.data.dataClass.CaseInfoTypeConverter
import android.study.imt_covid.data.network.response.ConnectivityInterceptor
import android.study.imt_covid.data.dataClass.TotalAndActiveResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val API_KEY = "daily_data"

// https://imt-covid19.herokuapp.com/vietnam/api?key=daily_data&filter_type=cases
interface APIdata {

    @GET(value = "api")
    fun getCurrentData(@Query(value = "filter_type") TotalAndActive: String)
            : Deferred<TotalAndActiveResponse>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): APIdata {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor)
                .build()
            val gson = GsonBuilder()
                .registerTypeAdapter(CaseInfo::class.java, CaseInfoTypeConverter())
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://imt-covid19.herokuapp.com/vietnam/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIdata::class.java)
        }

    }
}