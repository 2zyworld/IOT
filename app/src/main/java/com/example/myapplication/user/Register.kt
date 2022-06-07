package com.example.myapplication.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.MainActivity
import com.example.myapplication.MainActivity2
import com.example.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {



    lateinit var password1: EditText
    lateinit var password2: EditText
    lateinit var name: EditText

    lateinit var email: EditText

    lateinit var button: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.textemail)
        password1 = findViewById(R.id.registerpw1)
        password2 = findViewById(R.id.registerpw2)
        name = findViewById(R.id.textname)


        button = findViewById(R.id.button)



        var retrofit = Retrofit.Builder()
            .baseUrl("http://3.0.128.249:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var signupservice:Signupservice = retrofit.create(Signupservice::class.java)

        button.setOnClickListener{

            val emailStr= email.text.toString()
            val pwStr1 = password1.text.toString()
            val pwStr2 = password2.text.toString()
            val nameStr = name.text.toString()



            fun signupstate(signup_state : String) {
                if (signup_state == "0000") {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)


                }

            }


            val user = User(nameStr, pwStr1, pwStr2, emailStr )

            signupservice.register(user).enqueue(object: Callback<Login>
            {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        Log.d("회원가입", "${response}")
                        Log.d("회원가입", "${result}")
                        signupstate(signup_state = "0000")
                    } else {
                        Log.d("회원가입 에러 ", "${response.errorBody()!!.string()}")
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.e("회원가임", "${t.localizedMessage}")
                }


            })
            }



        }
    }

