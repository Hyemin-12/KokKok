package com.example.kokkok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kokkok.databinding.ActivityNaviBinding
import com.google.android.material.tabs.TabLayout

class NaviActivity : AppCompatActivity(),
    PopupMenu.OnMenuItemClickListener{
    private val TAG_LIST= "project_list_fragment"
    private val TAG_ADD = "add_project_fragment"
    private val TAG_MY_PAGE = "my_page_fragment"

    private lateinit var binding: ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 상단 액션바 숨기기
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아무것도 선택 안했을 때 -> ProjectListFragment
         setFragment(TAG_LIST, ProjectListFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            // 버튼 누르면 해당 Fragment로 이동
            when (item.itemId) {
                R.id.project_list -> setFragment(TAG_LIST, ProjectListFragment())
                R.id.add_project -> showPopup(binding.navigationView)
                R.id.my_page -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }

    }

    // Fragment 이동 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val projectList = manager.findFragmentByTag(TAG_LIST)
        val addProject = manager.findFragmentByTag(TAG_ADD)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        // 태그에 따라 Fragment 숨기고 보이도록 함
        if (projectList != null) {
            fragTransaction.hide(projectList)
        }

        if (addProject != null) {
            fragTransaction.hide(addProject)
        }

        if (myPage != null) {
            fragTransaction.hide(myPage)
        }

        if (tag == TAG_LIST) {
            if (projectList != null) {
                fragTransaction.show(projectList)
            }
        } else if (tag == TAG_ADD) {
            if (addProject != null) {
                fragTransaction.show(addProject)
            }
        } else if (tag == TAG_MY_PAGE) {
            if (myPage != null) {
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

    // 플러스 버튼 팝업창
    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v, Gravity.CENTER) // PopupMenu 객체 선언
        popup.menuInflater.inflate(R.menu.add_project_menu, popup.menu) // 메뉴 레이아웃 inflate
        popup.setOnMenuItemClickListener(this) // 메뉴 아이템 클릭 리스너 달아주기
        popup.show() // 팝업 보여주기
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) { // 메뉴 아이템에 따라 동작 다르게 하기
            // 팝업 메뉴에서 아이텝 클릭 시 해당 프래그먼트로 이동
            R.id.gift_menu -> {
                setContentView(R.layout.activity_main)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, AddGiftFragment())
                    .commit()
            }
            R.id.delivery_menu -> {
                setContentView(R.layout.activity_main)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, AddDeliveryFragment())
                    .commit()
            }
        }

        return item != null // 아이템이 null이 아닌 경우 true, null인 경우 false 리턴
    }
}