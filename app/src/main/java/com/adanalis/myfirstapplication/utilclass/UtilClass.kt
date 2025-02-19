package com.adanalis.myfirstapplication.utilclass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.adanalis.myfirstapplication.R

class UtilClass(
    private val activity: FragmentActivity
) {
    fun loadFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }
}