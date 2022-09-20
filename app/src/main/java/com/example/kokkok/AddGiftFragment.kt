package com.example.kokkok

import android.os.Bundle
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

class AddGiftFragment : Fragment() {

    private lateinit var imageView: ImageView
    val OPEN_CALLERY = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_gift_fragment, container, false)

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
        imageView = view.findViewById(R.id.background_image)
        val createButton = view.findViewById<Button>(R.id.create_btn)

        val title = inputTitle.text.toString()
        val content = inputContent.text.toString()
        val amount = inputAmount.text.toString()
        val participants = inputParticipants.text.toString()
        val password = inputPassword.text.toString()


        addImgButton.setOnClickListener{
            loadImage()
        }

        // 방 공개 여부
        disclosureStatus.setOnCheckedChangeListener { radioGroup, i ->
            when(i) {
                // 공개방이면 -> 비밀번호 입력창 안보이게
                 R.id.public_room-> {
                    passwordText.visibility = View.GONE
                    inputPassword.visibility = View.GONE
                }
                // 비공개면 -> 비밀번호 입력창 보이게
                R.id.private_room -> {
                    passwordText.visibility = View.VISIBLE
                    inputPassword.visibility = View.VISIBLE
                }

            }
        }

        // 캘린더 버튼 누르면 -> 날짜 선택 가능
        calendarButton.setOnClickListener() {
            //오늘 이후만 선택 가능
            val calendarConstraintBuilder = CalendarConstraints.Builder()
            calendarConstraintBuilder.setValidator(DateValidatorPointForward.now())

            // 날짜 선택 화면 구현
            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText("날짜 선택")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(calendarConstraintBuilder.build());

            val datePicker = builder.build()

            // 날짜 선택 하면 expirationDate에 저장
            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.time = Date(it)
                expirationDate.text = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
            }
            datePicker.show(childFragmentManager, datePicker.toString())
        }

        // 프로젝트 생성 버튼
        createButton.setOnClickListener {
            // 서버에 프로젝트 저장

            // 목록 페이지로 이동
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProjectListFragment())
                .commit()
        }

        return view
    }

    // 이미지 업로드
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

    // 갤러리에서 이미지 불러오기
    private fun loadImage() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, OPEN_CALLERY)
    }
}