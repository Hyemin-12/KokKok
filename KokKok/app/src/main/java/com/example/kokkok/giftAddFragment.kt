package com.example.kokkok

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import java.util.*

class giftAddFragment : Fragment() {

    private lateinit var imageView: ImageView
    val OPEN_CALLERY = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.gift_add_fragment, container, false)

        val addImgButton = view.findViewById<Button>(R.id.add_background_image_btn)
        val inputTitle = view.findViewById<EditText>(R.id.input_title)
        val inputContent = view.findViewById<EditText>(R.id.input_content)
        val inputAmount = view.findViewById<EditText>(R.id.input_amount)
        val inputParticipants = view.findViewById<EditText>(R.id.input_participants)
        val expirationDate = view.findViewById<TextView>(R.id.expiration_date)
        val calendarButton = view.findViewById<ImageButton>(R.id.calander_btn)
        val disclosureStatus = view.findViewById<RadioGroup>(R.id.disclosure_status)
        val passwordText = view.findViewById<TextView>(R.id.password_text)
        val inputPassword = view.findViewById<EditText>(R.id.input_password)
        imageView = view.findViewById<ImageView>(R.id.background_image)

        val title = inputTitle.text.toString()
        val content = inputContent.text.toString()
        val amount = inputAmount.text.toString()
        val participants = inputParticipants.text.toString()
        val password = inputPassword.text.toString()


        addImgButton.setOnClickListener{
            loadImage()
        }


        disclosureStatus.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                 R.id.public_room-> {
                    passwordText.visibility = View.GONE
                    inputPassword.visibility = View.GONE
                }
                R.id.private_room -> {
                    passwordText.visibility = View.VISIBLE
                    inputPassword.visibility = View.VISIBLE
                }

            }
        }

        calendarButton.setOnClickListener() {
            //오늘 이후만 선택 가능
            val calendarConstraintBuilder = CalendarConstraints.Builder()
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now())

            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("날짜 선택")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraintBuilder.build());

            val datePicker = builder.build()

            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)
                expirationDate.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
            }
            datePicker.show(childFragmentManager, datePicker.toString())
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
                    imageView.setImageBitmap(selectedImage)
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