package com.example.kokkok

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class AddDeliveryActivity : AppCompatActivity(){
    private lateinit var image_view: ImageView
    var dateString = ""
    var timeString = ""
    var cal = Calendar.getInstance()

    val OPEN_CALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_delivery_activity)

//        val name_text = findViewById<EditText>(R.id.name_text)
//        val intro_text = findViewById<EditText>(R.id.intro_text)
        val day_text = findViewById<TextView>(R.id.day_text)
        val time_text = findViewById<TextView>(R.id.time_text)
        val radio_Group = findViewById<RadioGroup>(R.id.radio_group)
        val password = findViewById<TextView>(R.id.password)
        val password_text = findViewById<EditText>(R.id.password_text)
        val back_img = findViewById<Button>(R.id.back_img)
        val calander_btn = findViewById<ImageButton>(R.id.calander_btn)
        val time_btn = findViewById<ImageButton>(R.id.time_btn)
        val creat_btn = findViewById<Button>(R.id.delivery_btn)


        image_view = findViewById<ImageView>(R.id.image_view)

        back_img.setOnClickListener{
            loadImage()
        }
        radio_Group.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                R.id.radio_pirates-> {
                    password.visibility = View.GONE
                    password_text.visibility = View.GONE
                }
                R.id.radio_ninjas -> {
                    password.visibility = View.VISIBLE
                    password_text.visibility = View.VISIBLE
                }

            }
        }

        calander_btn.setOnClickListener() {
            //calendar Constraint Builder 선택할수있는 날짜 구간설정
            val calendarConstraintBuilder = CalendarConstraints.Builder()
            //오늘 이후만 선택가능하게 하는 코드
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now())
            //오늘 이전만 선택가능하게 하는 코드
            //calendarConstraintBuilder.setValidator(DateValidatorPointBackward.now())


            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Set Dieday")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())

                //위에서 만든 calendarConstraint을 builder에 설정해줍니다.
                .setCalendarConstraints(calendarConstraintBuilder.build());

//아래부터는 목표1 코드방식과 동일합니다.

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)
                day_text.text = "${calendar.get(Calendar.YEAR) + 1}/${calendar.get(Calendar.MONTH)}/${calendar.get(Calendar.DAY_OF_MONTH)}"

            }
            datePicker.show(supportFragmentManager,datePicker.toString())
        }

        //time 선택 이미지 버튼

        time_btn.setOnClickListener() {
            val cal = Calendar.getInstance()

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, h, m ->
                timeString = "${h}시 ${m}분"
                time_text.text = timeString
            }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()

            // TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

//            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                timeString = "${hourOfDay}시 ${minute}분"
//                time_text.text = "날짜/시간 : "+dateString + " / " + timeString
//            }
//            TimePickerDialog(activity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }

        creat_btn.setOnClickListener {
            // 목록 페이지로 이동
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_CALLERY){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    val imageStream = this.contentResolver.openInputStream(dataUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    image_view.setImageBitmap(selectedImage)
                }catch (e:Exception){
                    Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadImage() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, OPEN_CALLERY)
    }

}