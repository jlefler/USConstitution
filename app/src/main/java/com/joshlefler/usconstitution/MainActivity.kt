package com.joshlefler.usconstitution

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val mainListFragment = MainListFragment();
            supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFrame, mainListFragment).commit()
        }
    }
}
