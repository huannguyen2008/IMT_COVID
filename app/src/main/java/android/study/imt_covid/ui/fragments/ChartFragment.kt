package android.study.imt_covid.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.study.imt_covid.R
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.ui.viewmodel.ChartViewModel
import android.study.imt_covid.ui.viewmodel.factory.ChartViewModelFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.chart_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ChartFragment(
) : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private lateinit var viewModel: ChartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chart_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: ChartViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ChartViewModel::class.java)
        bindUI()


    }

    private fun bindUI() = launch {
        val vnNation = viewModel.vnNation.await()
        vnNation.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer


//          draw pie chart for nationality
            val nationality = ArrayList<PieEntry>()
            for ((nation, number) in it) {
                nationality.add(PieEntry(number.toFloat(), nation))
            }
            val dataSet = PieDataSet(nationality, "Cases by Nationality")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0F, 60F)
            dataSet.selectionShift = 5f
            dataSet.setColors(R.color.colorPrimary)
//            val total = it.male + it.female
            val data = PieData(dataSet)
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            nation_pie_chart.data = data
            nation_pie_chart.setUsePercentValues(true)
            nation_pie_chart.setDrawEntryLabels(false)
            nation_pie_chart.highlightValues(null)
            nation_pie_chart.invalidate()
            nation_pie_chart.description.isEnabled = false
            nation_pie_chart.legend.isEnabled = false
            nation_pie_chart.setCenterTextSize(11f);
            nation_pie_chart.setCenterTextColor(Color.BLACK);
        })

        val vnGender = viewModel.vnGender.await()
        vnGender.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            // draw pie chart for gender
            val gender = ArrayList<PieEntry>()
            gender.add(PieEntry(it.male.toFloat(), "Male(%)"))
            gender.add(PieEntry(it.female.toFloat(), "Female(%)"))
            val dataSet = PieDataSet(gender, "Cases by Gender")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(60F, 0F)
            dataSet.selectionShift = 5f
            dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
//            val total = it.male + it.female
            val data = PieData(dataSet)
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            gender_pie_chart.data = data
            gender_pie_chart.setUsePercentValues(true)
            gender_pie_chart.setDrawEntryLabels(false)
            gender_pie_chart.highlightValues(null)
            gender_pie_chart.invalidate()
            gender_pie_chart.description.isEnabled = false
//            pie_chart.centerText = "Total: $total"
            gender_pie_chart.setCenterTextSize(11f);
            gender_pie_chart.setCenterTextColor(Color.BLACK);
        })
//        val vnAge = viewModel.vnAge.await()
//        vnAge.observe(viewLifecycleOwner, Observer {
//            if (it == null) return@Observer
//            // draw age bar chart
//            val age = ArrayList<BarEntry>()
//            age.add(BarEntry(945f, 0f))
//            age.add(BarEntry(1040f, 1f))
//            age.add(BarEntry(1100f, 2f))
//            age.add(BarEntry(1369f, 3f))
//            age.add(BarEntry(1487f, 4f))
//            age.add(BarEntry(1487f, 5f))
//            age.add(BarEntry(1487f, 6f))

//            val labels: BarDataSet = BarDataSet(List<>)
//            labels.add("1-15").toString()
//            labels.add("16-30").toString()
//            labels.add("31-45").toString()
//            labels.add("46-60").toString()
//            labels.add("61-75").toString()
//            labels.add("76-90").toString()
//            labels.add("91-105").toString()
//            val data = BarDataSet(age, "Cases by Age")
//            val dataSet = BarData(labels, data);
//            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//            age_bar_chart.data = data;
//            age_bar_chart.animateY(5000);

//        })
    }
}