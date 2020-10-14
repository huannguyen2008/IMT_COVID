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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.play.core.assetpacks.j
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
        //age line chart
//        val entries = ArrayList<Entry>()
//
//        entries.add(Entry(1f, 10f))
//        entries.add(Entry(2f, 2f))
//        entries.add(Entry(3f, 7f))
//        entries.add(Entry(4f, 20f))
//        entries.add(Entry(5f, 16f))
//
//        val vl = LineDataSet(entries, "My Type")
//
//        vl.setDrawValues(false)
//        vl.setDrawFilled(true)
//        vl.lineWidth = 3f
//        vl.fillColor = R.color.colorPrimary
//        vl.fillAlpha = R.color.red
//
//        age_line_chart.xAxis.labelRotationAngle = 0f
//
//        age_line_chart.data = LineData(vl)
//        age_line_chart.axisRight.isEnabled = false
//        age_line_chart.xAxis.axisMaximum = 0.1f
//        age_line_chart.setTouchEnabled(true)
//        age_line_chart.setPinchZoom(true)
//
//        age_line_chart.description.text = "Days"
//        age_line_chart.setNoDataText("No forex yet!")
//
//        age_line_chart.animateX(1800, Easing.EaseInExpo)
    }

    private fun bindUI() = launch {
        val vnNation = viewModel.vnNation.await()
        vnNation.observe(viewLifecycleOwner, Observer { it ->
            if (it == null) return@Observer
//            test_text.text = it.toString()
//          draw pie chart for nationality
            val numberCaseSorted = it.sortedByDescending { it.numberCases }.toMutableList()
            val nationality = ArrayList<PieEntry>()
            for ((nation, number) in numberCaseSorted.take(3)) {
                nationality.add(PieEntry(number.toFloat(), nation))
            }
            val other = numberCaseSorted.subList(2,numberCaseSorted.lastIndex)
            nationality.add(PieEntry(other.map { it.numberCases }.sum().toFloat(),"Other"))
            val dataSet = PieDataSet(nationality, "")
            val data = PieData(dataSet)
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            nation_pie_chart.description.isEnabled = false
            nation_pie_chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            nation_pie_chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            nation_pie_chart.legend.orientation = Legend.LegendOrientation.VERTICAL
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0F, 60F)
            dataSet.selectionShift = 5f
            nation_pie_chart.data = data
            nation_pie_chart.setUsePercentValues(true)
            nation_pie_chart.setDrawEntryLabels(false)
            nation_pie_chart.highlightValues(null)
            nation_pie_chart.invalidate()

        })

        // draw gender chart
        val vnGender = viewModel.vnGender.await()
        vnGender.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            // draw pie chart for gender
            val gender = ArrayList<PieEntry>()
            gender.add(PieEntry(it.male.toFloat(), "Male(%)"))
            gender.add(PieEntry(it.female.toFloat(), "Female(%)"))
            val dataSet = PieDataSet(gender, "")
            gender_pie_chart.description.isEnabled = false
            gender_pie_chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            gender_pie_chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            gender_pie_chart.legend.orientation = Legend.LegendOrientation.VERTICAL
            dataSet.setColors(*ColorTemplate.PASTEL_COLORS)
            val data = PieData(dataSet)
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            gender_pie_chart.data = data
            gender_pie_chart.setUsePercentValues(true)
            gender_pie_chart.setDrawEntryLabels(false)
            gender_pie_chart.highlightValues(null)
            gender_pie_chart.invalidate()


        })
//        val vnAge = viewModel.vnAge.await()
//        vnAge.observe(viewLifecycleOwner, Observer {
//            if (it == null) return@Observer
//            // draw age bar chart
//        })

    }
}