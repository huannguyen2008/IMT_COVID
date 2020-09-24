package android.study.imt_covid

import android.app.Application
import android.study.imt_covid.data.dtbAndDAO.CovidDtb
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.data.network.networkSource.DataSource
import android.study.imt_covid.data.network.networkSource.DataSourceImpl
import android.study.imt_covid.data.network.Interceptor.ConnectivityInterceptor
import android.study.imt_covid.data.network.Interceptor.ConnectivityInterceptorImpl
import android.study.imt_covid.data.repository.CovidRepository
import android.study.imt_covid.data.repository.CovidRepositoryImpl
import android.study.imt_covid.ui.viewmodel.HealthViewModelFactory
import android.study.imt_covid.ui.viewmodel.HomeViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppApplication))

        bind() from singleton { CovidDtb(instance()) }

        bind() from singleton { instance<CovidDtb>().VnSummaryDAO() }
        bind() from singleton { instance<CovidDtb>().VnCityDAO() }
        bind() from singleton { instance<CovidDtb>().VnNationalityDAO() }
        bind() from singleton { instance<CovidDtb>().VnGenderDAO() }
        bind() from singleton { instance<CovidDtb>().VnAgeDAO() }


        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { APIdata(instance()) }
        bind<DataSource>() with singleton { DataSourceImpl(instance()) }
        bind<CovidRepository>() with singleton {
            CovidRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { HealthViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}