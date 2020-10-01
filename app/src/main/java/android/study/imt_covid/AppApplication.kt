package android.study.imt_covid

import android.app.Application
import android.study.imt_covid.data.dtbAndDAO.CovidDtb
import android.study.imt_covid.network.APIdata
import android.study.imt_covid.network.networkSource.DataSource
import android.study.imt_covid.network.networkSource.DataSourceImpl
import android.study.imt_covid.network.interceptor.ConnectivityInterceptor
import android.study.imt_covid.network.interceptor.ConnectivityInterceptorImpl
import android.study.imt_covid.repository.CovidRepository
import android.study.imt_covid.repository.CovidRepositoryImpl
import android.study.imt_covid.ui.viewmodel.factory.ChartViewModelFactory
import android.study.imt_covid.ui.viewmodel.factory.HomeViewModelFactory
import android.study.imt_covid.ui.viewmodel.factory.InformationViewModelFactory
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
        bind() from singleton { instance<CovidDtb>().WorldSummaryDAO() }
        bind() from singleton { instance<CovidDtb>().VnCityDAO() }
        bind() from singleton { instance<CovidDtb>().VnNationalityDAO() }
        bind() from singleton { instance<CovidDtb>().VnGenderDAO() }
        bind() from singleton { instance<CovidDtb>().VnAgeDAO() }
        bind() from singleton { instance<CovidDtb>().LastUpdateDAO() }
        bind() from singleton { instance<CovidDtb>().CountrySummaryDAO() }

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
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { InformationViewModelFactory(instance()) }
        bind() from provider { ChartViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}