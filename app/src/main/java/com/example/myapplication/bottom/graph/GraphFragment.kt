package com.example.myapplication.bottom.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGraphBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet


class GraphFragment : Fragment() {

    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!


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


        val entries = ArrayList<BarEntry>()

        entries.add(BarEntry(1.2f,20.0f))
        entries.add(BarEntry(2.2f,70.0f))
        entries.add(BarEntry(3.2f,30.0f))
        entries.add(BarEntry(4.2f,90.0f))
        entries.add(BarEntry(5.2f,70.0f))
        entries.add(BarEntry(6.2f,30.0f))
        entries.add(BarEntry(7.2f,90.0f))

        binding.barchart.run {
            description.isEnabled = false //차트 옆에 별도로 표기되는 description이다. false로 설정하여 안보이게 했다.
            setMaxVisibleValueCount(7) // 최대 보이는 그래프 개수를 7개로 정해주었다.
            setPinchZoom(false) // 핀치줌(두손가락으로 줌인 줌 아웃하는것) 설정
            setDrawBarShadow(false)//그래프의 그림자
            setDrawGridBackground(false)//격자구조 넣을건지

            axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
                axisMaximum = 101f //100 위치에 선을 그리기 위해 101f로 맥시멈을 정해주었다
                axisMinimum = 0f // 최소값 0
                granularity = 50f // 50 단위마다 선을 그리려고 granularity 설정 해 주었다.

                setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(false) // 축 그리기 설정
                axisLineColor = ContextCompat.getColor(context,R.color.colorPrimary) // 축 색깔 설정
                gridColor = ContextCompat.getColor(context,R.color.colorPrimary) // 축 아닌 격자 색깔 설정
                textColor = ContextCompat.getColor(context,R.color.black) // 라벨 텍스트 컬러 설정
                textSize = 14f //라벨 텍스트 크기
            }

            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM//X축을 아래에다가 둔다.
                granularity = 1f // 1 단위만큼 간격 두기
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자
                textColor = ContextCompat.getColor(context, com.google.android.material.R.color.m3_ref_palette_black )//라벨 색상
                valueFormatter = MyXAxisFormatter() // 축 라벨 값 바꿔주기 위함
                textSize = 14f // 텍스트 크기
            }
            axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
            setTouchEnabled(false) // 그래프 터치해도 아무 변화없게 막음
            animateY(1000) // 밑에서부터 올라오는 애니매이션 적용
            legend.isEnabled = false //차트 범례 설정

            var set = BarDataSet(entries,"DataSet")//데이터셋 초기화 하기
            set.color = ContextCompat.getColor(requireContext(),R.color.black)

            val dataSet :ArrayList<IBarDataSet> = ArrayList()
            dataSet.add(set)
            val data = BarData(dataSet)
            data.barWidth = 0.3f//막대 너비 설정하기
            binding.barchart.run {
                this.data = data //차트의 데이터를 data로 설정해줌.
                setFitBars(true)
                invalidate()
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}










//        var pieChart: PieChart = findViewById(R.id.country_pc_piechart)
//
//        val entries = ArrayList<PieEntry>()
//        entries.add(PieEntry(1.2f,20.0f))
//        entries.add(PieEntry(2.2f,70.0f))
//        entries.add(PieEntry(3.2f,30.0f))
//        entries.add(PieEntry(4.2f,90.0f))
//        entries.add(PieEntry(5.2f,70.0f))
//        entries.add(PieEntry(6.2f,30.0f))
//        entries.add(PieEntry(7.2f,90.0f))
//
//        pieChart.run{
//            description.isEnabled = false
//
//        }
//
//        val dataSet: PieDataSet = PieDataSet(yValues, "")
//                with(dataSet) {
//                    sliceSpace = 3f
//                    selectionShift = 5f
//                    setColors(*ColorTemplate.JOYFUL_COLORS)
//                }
//


//        countryResponse.observe(viewLifecycleOwner, Observer {
//            with(binding.countryPcPiechart) {
//                setUsePercentValues(true)
//                description.isEnabled = false
//                setExtraOffsets(5f, 10f, 5f, 5f)
//                isDrawHoleEnabled = true
//                setHoleColor(Color.WHITE)
//                transparentCircleRadius = 61f
//
//                val yValues: ArrayList<PieEntry> = ArrayList()
//                with(yValues) {
//                    add(PieEntry(it.city1p.toFloat(), it.city1n))
//                    add(PieEntry(it.city2p.toFloat(), it.city2n))
//                    add(PieEntry(it.city3p.toFloat(), it.city3n))
//                    add(PieEntry(it.city4p.toFloat(), it.city4n))
//                    add(PieEntry(it.city5p.toFloat(), it.city5n))
//                }
//
//                animateY(1000, Easing.EaseInOutCubic)
//
//                val dataSet: PieDataSet = PieDataSet(yValues, "")
//                with(dataSet) {
//                    sliceSpace = 3f
//                    selectionShift = 5f
//                    setColors(*ColorTemplate.JOYFUL_COLORS)
//                }
//
//                val pieData: PieData = PieData(dataSet)
//                with(pieData) {
//                    setValueTextSize(10f)
//                    setValueTextColor(Color.BLACK)
//                    val des = Description()
//                    des.text = "무제"
//                    description = des
//                }
//                data = pieData
//            }
//        })
