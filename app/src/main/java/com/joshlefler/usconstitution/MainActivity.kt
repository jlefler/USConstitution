package com.joshlefler.usconstitution

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val mainListFragment = MainListFragment();
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, mainListFragment).commit()
    }
}
