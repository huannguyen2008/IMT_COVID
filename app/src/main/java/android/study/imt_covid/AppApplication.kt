package android.study.imt_covid

import android.app.Application
import android.study.imt_covid.data.CasesDatabase
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.data.network.CasesNetworkDataSource
import android.study.imt_covid.data.network.CasesNetworkDataSourceImpl
import android.study.imt_covid.data.network.response.ConnectivityInterceptor
import android.study.imt_covid.data.network.response.ConnectivityInterceptorImpl
import android.study.imt_covid.data.repository.CasesRepository
import android.study.imt_covid.data.repository.CasesRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class AppApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppApplication))

        bind() from singleton { CasesDatabase(instance()) }
        bind() from singleton { instance<CasesDatabase>().caseInfoDAO() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { APIdata(instance()) }
        bind<CasesNetworkDataSource>() with singleton { CasesNetworkDataSourceImpl(instance()) }
        bind<CasesRepository>() with singleton { CasesRepositoryImpl(instance(),instance()) }



    }
}