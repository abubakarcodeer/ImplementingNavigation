package com.adanalis.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.adanalis.myfirstapplication.databinding.ActivityDrawerNavigationBinding
import com.adanalis.myfirstapplication.utilclass.UtilClass

class DrawerNavigation : AppCompatActivity() {
    private lateinit var binding:ActivityDrawerNavigationBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private val util = UtilClass(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.apply {
            util.loadFragment(FragmentHome())
            actionBarDrawerToggle = ActionBarDrawerToggle(this@DrawerNavigation,drawLayout,R.string.navigation_open,R.string.navigation_close)
            drawLayout.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()
            actionBarDrawerToggle.isDrawerIndicatorEnabled = true
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            navView.setNavigationItemSelectedListener {

                when(it.itemId){
                    R.id.navMenu-> startActivity(Intent(this@DrawerNavigation,BottomNavigationActivity::class.java))
                }
                drawLayout.closeDrawers()
                true
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}