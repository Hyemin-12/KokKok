package com.example.kokkok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.kokkok.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val api = RetroInterface.create()
    lateinit var allUser : ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.signup_activity)

        val completeBtn : Button = findViewById(R.id.complete_btn)
        val nameInput : TextView = findViewById(R.id.name_input)
        val emailInput : TextView = findViewById(R.id.email_input)
        val pwInput : TextView = findViewById(R.id.pw_input)
        val checkPw : TextView = findViewById(R.id.check_pw)


        api.allUser().enqueue(object: retrofit2.Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                allUser = response.body()?:return
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {

            }
        })

        completeBtn.setOnClickListener{
            binding.apply {
                val name = nameInput.text.toString()
                val id = emailInput.text.toString()
                val pw = pwInput.text.toString()
                val checkpw = checkPw.text.toString()

                if(id == "" || pw == "" || name =="" || checkpw == "") {
                    Toast.makeText(applicationContext, "입력하지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(pw != checkpw) {
                    Toast.makeText(applicationContext, "비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val newUser = RegisterModel(emailInput.text.toString(),pwInput.text.toString(), nameInput.text.toString())
            api.register(newUser).enqueue(object: retrofit2.Callback<RegisterResult>{
                override fun onResponse(call: Call<RegisterResult>, response: Response<RegisterResult>) {
                    val result = response.body()?.message ?: return
                    if(result)
                        Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(applicationContext, "회원가입 실패, 이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<RegisterResult>, t: Throwable) {
                    Log.d("testt", t.message.toString())
                }
            })
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userList", allUser)
            startActivity(intent)
        }
    }
}