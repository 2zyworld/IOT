package com.example.myapplication.bottom.graph

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.bottom.graph.data.graphdata
import com.example.myapplication.bottom.graph.data.graphdataItem
import com.example.myapplication.databinding.FragmentGraphBinding
import com.example.myapplication.drawer.meditation.data.data
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS
import kotlinx.android.synthetic.main.fragment_graph.*
import java.lang.reflect.Array.getDouble

class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!

    private var angry:Double? = null

    inner class MyXAxisFormatter : ValueFormatter(){
        private val days = arrayOf("1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()-1) ?: value.toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.chart.setUsePercentValues(true)



        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(508f,"Angry"))
        entries.add(PieEntry(600f,"Anticipate"))
        entries.add(PieEntry(750f,"Joy"))
        entries.add(PieEntry(508f,"Fear"))
        entries.add(PieEntry(670f,"Other"))

        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)

        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries,"")
            pieDataSet.apply {
                colors = colorsItems
                valueTextColor = Color.BLACK
                valueTextSize = 16f

                val pieData = PieData(pieDataSet)
                binding.chart.apply {
                    data = pieData
                    description.isEnabled = false
                    isRotationEnabled = true
                    centerText = ""
                    setEntryLabelColor(Color.BLACK)
                    animateY(1400, Easing.EaseInOutQuad)
                    animate()
                }


//--------------------------------------------------- barchart


                val entries = ArrayList<BarEntry>()

                entries.add(BarEntry(1.2f, 20.0f))
                entries.add(BarEntry(2.2f, 70.0f))
                entries.add(BarEntry(3.2f, 30.0f))
                entries.add(BarEntry(4.2f, 90.0f))
                entries.add(BarEntry(5.2f, 70.0f))
                entries.add(BarEntry(6.2f, 30.0f))
                entries.add(BarEntry(7.2f, 90.0f))
                entries.add(BarEntry(8.2f, 50.0f))
                entries.add(BarEntry(9.2f, 20.0f))
                entries.add(BarEntry(10.2f, 30.0f))
                entries.add(BarEntry(11.2f, 10.0f))
                entries.add(BarEntry(12.2f, 90.0f))


                binding.barchart.run {
                    description.isEnabled =
                        false //차트 옆에 별도로 표기되는 description이다. false로 설정하여 안보이게 했다.
                    setMaxVisibleValueCount(7) // 최대 보이는 그래프 개수를 7개로 정해주었다.
                    setPinchZoom(true) // 핀치줌(두손가락으로 줌인 줌 아웃하는것) 설정
                    setDrawBarShadow(false)//그래프의 그림자
                    setDrawGridBackground(false)//격자구조 넣을건지

                    axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                        axisMaximum = 101f //100 위치에 선을 그리기 위해 101f로 맥시멈을 정해주었다
                        axisMinimum = 0f // 최소값 0
                        granularity = 50f // 50 단위마다 선을 그리려고 granularity 설정 해 주었다.

                        setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
                        setDrawGridLines(true) //격자 라인 활용
                        setDrawAxisLine(false) // 축 그리기 설정
                        axisLineColor =
                            ContextCompat.getColor(context, R.color.colorPrimary) // 축 색깔 설정
                        gridColor =
                            ContextCompat.getColor(context, R.color.colorPrimary) // 축 아닌 격자 색깔 설정
                        textColor = ContextCompat.getColor(context, R.color.black) // 라벨 텍스트 컬러 설정
                        textSize = 14f //라벨 텍스트 크기
                    }

                    xAxis.run {
                        position = XAxis.XAxisPosition.BOTTOM//X축을 아래에다가 둔다.
                        granularity = 1f // 1 단위만큼 간격 두기
                        setDrawAxisLine(true) // 축 그림
                        setDrawGridLines(false) // 격자
                        textColor = ContextCompat.getColor(
                            context,
                            com.google.android.material.R.color.m3_ref_palette_black
                        )//라벨 색상
                        valueFormatter = MyXAxisFormatter() // 축 라벨 값 바꿔주기 위함
                        textSize = 12f // 텍스트 크기
                    }
                    axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
                    setTouchEnabled(false) // 그래프 터치해도 아무 변화없게 막음
                    animateY(1000) // 밑에서부터 올라오는 애니매이션 적용
                    legend.isEnabled = false //차트 범례 설정


                    var set = BarDataSet(entries, "DataSet")//데이터셋 초기화 하기
                    set.color = ContextCompat.getColor(requireContext(), R.color.black)

                    val dataSet: ArrayList<IBarDataSet> = ArrayList()

                    dataSet.add(set)
                    val data = BarData(dataSet)

                    data.barWidth = 0.5f//막대 너비 설정하기
                    binding.barchart.run {
                        this.data = data //차트의 데이터를 data로 설정해줌.
                        setFitBars(true)
                        invalidate()
                    }
                }
                //-----------------------------------------------------radar chart


                val entry = ArrayList<RadarEntry>()

                entry.add(RadarEntry(1.2f, 20.0f))
                entry.add(RadarEntry(2.2f, 70.0f))
                entry.add(RadarEntry(3.2f, 30.0f))
                entry.add(RadarEntry(4.2f, 90.0f))
                entry.add(RadarEntry(5.2f, 70.0f))
                entry.add(RadarEntry(6.2f, 30.0f))
                entry.add(RadarEntry(7.2f, 90.0f))
                entry.add(RadarEntry(8.2f, 50.0f))


                val radarDataSet = RadarDataSet(entry, "")
                radarDataSet.apply {
                    colors = colorsItems
                    valueTextColor = Color.BLACK
                    valueTextSize = 16f

                    val radarData = RadarData(radarDataSet)
                    binding.radarchart.apply {

                        data = radarData
                        description.isEnabled = false
                        isRotationEnabled = true

//                        setEntryLabelColor(Color.BLACK)
                        animateY(1400, Easing.EaseInOutQuad)
                        animate()




                        return root
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
            
    }
    






