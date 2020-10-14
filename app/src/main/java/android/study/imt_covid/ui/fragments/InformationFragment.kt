package android.study.imt_covid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.internal.getLink
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.ui.viewmodel.InformationViewModel
import android.study.imt_covid.ui.viewmodel.factory.InformationViewModelFactory
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_about_us.*
import kotlinx.android.synthetic.main.fragment_information.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class InformationFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private lateinit var viewModel: InformationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: InformationViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(InformationViewModel::class.java)
        getLink(gov_link)
        getLink(who_link)
        getLink(ecdc_link)
        getLink(jhu_link)
        getLink(fb_link)
    }


}