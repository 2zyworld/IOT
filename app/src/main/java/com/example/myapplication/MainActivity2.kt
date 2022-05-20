package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.user.Login
import com.example.myapplication.user.LoginService
import com.example.myapplication.user.Register
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    lateinit var btnRegister: TextView
    var login_state = ""

    var login: Login? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnRegister = findViewById(R.id.btnRegister)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://a68039ed238d849789d17c141e432fe9-188230756.ap-southeast-1.elb.amazonaws.com:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService: LoginService = retrofit.create(LoginService::class.java)


        button.setOnClickListener {
            var text1 = registerid.text.toString()
            var text2 = registerpw1.text.toString()

            fun Loginstate(login_state : String) {
                if (login_state == "0000") {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    this.login_state = ""
                }
            }

            loginService.requestLogin(text1, text2).enqueue(object : Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    var dialog = AlertDialog.Builder(this@MainActivity2)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출실패했습니다.")
                    dialog.show()
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    login = response.body()
                    Log.d("LOGIN", "msg : " + login?.msg)
                    Log.d("LOGIN", "code : " + login?.code)
                    var dialog = AlertDialog.Builder(this@MainActivity2)
//                    dialog.setTitle(login?.msg)
//                    dialog.setMessage(login?.code)
//                    dialog.show()



                    login_state = login?.code.toString()
                    Loginstate(login_state)

                }

            })
            Log.d("LOGIN_STATE",login?.code.toString())


        }

        btnRegister.setOnClickListener {
            Intent(this, Register::class.java).run {
                startActivity(this)
            }
        }
    }
}