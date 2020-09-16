package android.study.imt_covid.fragments

import android.content.Intent
import android.os.Bundle
import android.study.imt_covid.ChartActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.data.API.APIdata
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        view.test_button.setOnClickListener {
            val intent = Intent (activity, ChartActivity::class.java)
            activity?.startActivity(intent)
        }
        val apiService = APIdata()
        GlobalScope.launch(Dispatchers.Main){
            val apiResponse = apiService.getCurrentData("cases").await()
            test_text.text = apiResponse.toString()
        }
        return view
    }
}

