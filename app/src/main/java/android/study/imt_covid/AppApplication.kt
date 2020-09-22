package android.study.imt_covid

import android.app.Application
import android.study.imt_covid.data.dtbAndDAO.VnSummaryDtb
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.data.network.networkSource.VnSummarySource
import android.study.imt_covid.data.network.networkSource.VnSummarySourceImpl
import android.study.imt_covid.data.network.response.ConnectivityInterceptor
import android.study.imt_covid.data.network.response.ConnectivityInterceptorImpl
import android.study.imt_covid.data.repository.VnSummaryRepository
import android.study.imt_covid.data.repository.VnSummaryRepositoryImpl
import android.study.imt_covid.viewmodel.HomeViewModelFactory
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

        bind() from singleton { VnSummaryDtb(instance()) }
        bind() from singleton { instance<VnSummaryDtb>().VnSummaryDAO() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { APIdata(instance()) }
        bind<VnSummarySource>() with singleton { VnSummarySourceImpl(instance()) }
        bind<VnSummaryRepository>() with singleton { VnSummaryRepositoryImpl(instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}