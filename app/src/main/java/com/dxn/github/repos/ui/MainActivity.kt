package com.dxn.github.repos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dxn.github.repos.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}