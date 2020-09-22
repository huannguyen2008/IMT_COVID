package android.study.imt_covid.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.activities.ChartActivity
import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.network.APIdata
import android.study.imt_covid.data.network.networkSource.VnSummarySourceImpl
import android.study.imt_covid.data.network.response.ConnectivityInterceptorImpl
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.viewmodel.HomeViewModel
import android.study.imt_covid.viewmodel.HomeViewModelFactory
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        view.test_button.setOnClickListener {
            val intent = Intent(activity, ChartActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: HomeViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        bindUI()
//        val apiService = APIdata(ConnectivityInterceptorImpl(this.context!!))
//        val VnSummarySource = VnSummarySourceImpl(apiService)
//        VnSummarySource.downloadedVnSummary.observe(viewLifecycleOwner, {
//            test_text.text = it.toString()
//        })
//        val diff = 0
//        val recover = 0
//        val totalDeath = 0
//        val active = 0
//        val total = 0
//        val newCases = 0
//        val newDeath = 0
//        val vnSummaryList = VnSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
//        GlobalScope.launch(Dispatchers.Main){
//            VnSummarySource.fetchVnSummary(vnSummaryList)
//        }
    }

    private fun bindUI() = launch {
        val homeFragment = viewModel.VnSummaryRepository.getVnSummary(
            VnSummary(
                0,
                0,
                0,
                0,
                0,
                0,
                0
            )
        )
        Log.d("Tedt", "After")
        homeFragment.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                test_text.text = "Null"
                return@Observer
            }
            test_text.text = it.toString()
        })
    }
}
