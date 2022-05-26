package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.bottom.dairy.dairy_post.UserName
import com.example.myapplication.user.Login
import com.example.myapplication.user.LoginService
import com.example.myapplication.user.Register
import com.kakao.util.helper.Utility
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

class MainActivity2 : AppCompatActivity() {

    lateinit var btnRegister: TextView
    var login_state = ""

    var login: Login? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnRegister = findViewById(R.id.btnRegister)
        val keyHash = Utility.getKeyHash(this /* context */)
        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.215.200.30:8000")
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
                    UserName.username = text1
                }
            }

            fun getAppKeyHash() {
                try {
                    val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                    for (signature in info.signatures) {
                        var md: MessageDigest
                        md = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        val something = String(Base64.encode(md.digest(), 0))
                        Log.e("Hash key", something)
                    }
                } catch (e: Exception) {
                    // TODO Auto-generated catch block
                    Log.e("name not found", e.toString())
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

                    dialog.setMessage("${login?.msg}")
                    dialog.show()



                    login_state = login?.code.toString()
                    Loginstate(login_state)
                    getAppKeyHash()

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