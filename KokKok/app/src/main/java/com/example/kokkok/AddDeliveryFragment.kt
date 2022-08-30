package com.example.kokkok

import android.app.Activity.RESULT_OK
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class AddDeliveryFragment : Fragment(){
    private lateinit var image_view: ImageView
    var dateString = ""
    var timeString = ""
    var cal = Calendar.getInstance()

    val OPEN_CALLERY = 1

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.add_delivery_fragment,container,false)

        val name_text = view.findViewById<EditText>(R.id.name_text)
        val intro_text =view.findViewById<EditText>(R.id.intro_text)
        val day_text = view.findViewById<TextView>(R.id.day_text)
        val time_text = view.findViewById<TextView>(R.id.time_text)
        val radio_Group = view.findViewById<RadioGroup>(R.id.radio_group)
        val password = view.findViewById<TextView>(R.id.password)
        val password_text = view.findViewById<EditText>(R.id.password_text)
        val back_img = view.findViewById<Button>(R.id.back_img)
        val calander_btn = view.findViewById<ImageButton>(R.id.calander_btn)
        val time_btn = view.findViewById<ImageButton>(R.id.time_btn)


        image_view = view.findViewById<ImageView>(R.id.image_view)

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
                day_text.text = "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.YEAR)}"

            }
            datePicker.show(childFragmentManager,datePicker.toString())
        }

        //time 선택 이미지 버튼

        time_btn.setOnClickListener() {
            val cal = Calendar.getInstance()

            TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { timePicker, h, m ->
                timeString = "${h}시 ${m}분"
                time_text.text = "날짜/시간 : "+dateString + " / " + timeString
            }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()

            // TimePickerDialog(context, TimePickerDialog.OnTimeSetListener(), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

//            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                timeString = "${hourOfDay}시 ${minute}분"
//                time_text.text = "날짜/시간 : "+dateString + " / " + timeString
//            }
//            TimePickerDialog(activity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_CALLERY){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    val imageStream = requireActivity().contentResolver.openInputStream(dataUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    image_view.setImageBitmap(selectedImage)
                }catch (e:Exception){
                    Toast.makeText(activity,"$e",Toast.LENGTH_SHORT).show()
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