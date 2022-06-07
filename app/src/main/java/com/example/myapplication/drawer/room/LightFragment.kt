package com.example.myapplication.drawer.room

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentLightBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.ColorUtil.parseColor
import org.eclipse.paho.client.mqttv3.MqttMessage

private const val SUB_TOPIC = "Android" //받아오기
private const val LIGTH_TOPIC = "Iot/light"
private const val SERVER_URI = "tcp://172.30.1.2"

var test_data = ""



lateinit var text : String
lateinit var arr: List<String>

class LightFragment : Fragment() {

    private var _binding: FragmentLightBinding? = null
    private val binding get() = _binding!!

    var colorsetHex = ""
    var colorset = 0

    lateinit var mqttClient: Mqtt
    lateinit var msg : String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 초기 값 보내기




        mqttClient = Mqtt(context, SERVER_URI)
        try {
            // mqttClient.setCallback { topic, message ->}
            mqttClient.setCallback(::onReceived)
            mqttClient.connect(arrayOf<String>(SUB_TOPIC))

        } catch (e: Exception) {
            e.printStackTrace()
        }


        _binding = FragmentLightBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.pickColorButton.setOnClickListener {
            context?.let { it1 ->
                ColorPickerDialog
                    .Builder(it1)        				// Pass Activity Instance
                    .setTitle("Pick Theme")           	// Default "Choose Color"
                    .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                    .setDefaultColor(parseColor("#ffffff"))     // Pass Default Color
                    .setColorListener { color, colorHex ->
                        // Handle Color Selection
                        colorset = color
                        colorsetHex = colorHex
                    }
                    .show()
            }
        }

        binding.setColorButton.setOnClickListener {


            binding.previewSelectedColor.setBackgroundColor(colorset)

            // mqtt publis
            mqttClient.publish(LIGTH_TOPIC, "init,android_mood,${colorsetHex},on")
            Log.i("Mqtt_result", "Light Setting Success")
            Toast.makeText(context,"Light Setting Success", Toast.LENGTH_SHORT).show()
        }
        binding.switch1.isChecked = true
        binding.switch1.setOnCheckedChangeListener{ compoundButton, b ->
            if(b){
                binding.switch1.isChecked = true
                binding.pickColorButton.setEnabled(true);
                binding.setColorButton.setEnabled(true);
            }
            else{
                binding.switch1.isChecked  = false
                binding.pickColorButton.setEnabled(false);
                binding.setColorButton.setEnabled(false);
                mqttClient.publish(LIGTH_TOPIC, "init,android_mood,#fffffff,off")
            }

            }
        binding.recommendButton.setOnClickListener{
            mqttClient.publish("data/light", "recommend,setting,light,on")
        }



        return root

    }
    fun onReceived(topic: String?, message: MqttMessage) {
        // 토픽 수신 처리
        val msg = String(message.payload)
        Log.i("Mqtt_result","수신메세지: $msg")
        test_data = msg //문자열, 비트
        arr = test_data.split(",")
        Log.i("Mqtt_result", "수신] 데이터 값 : $test_data// $arr")
        Log.i("Mqtt_result","수신메세지: "+test_data)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

}