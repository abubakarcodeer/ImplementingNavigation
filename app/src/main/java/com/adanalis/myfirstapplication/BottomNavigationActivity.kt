package com.adanalis.myfirstapplication

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.adanalis.myfirstapplication.databinding.ActivityBottomNavigationBinding
import com.adanalis.myfirstapplication.utilclass.UtilClass

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding
    private val util =UtilClass(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        util.loadFragment(FragmentHome())
        binding.apply {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.iconHome -> {
                       util.loadFragment(FragmentHome())
                    }

                    R.id.iconMessege -> {
                        util.loadFragment(FragmentMessege())
                    }

                    R.id.iconSetting -> {
                       util.loadFragment(FragmentSetting())
                    }
                }
                true
            }
        }

    }

}