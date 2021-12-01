package com.nikasov.cleanarchitectureapp.presentation.activity

import android.os.Bundle
import com.nikasov.cleanarchitectureapp.databinding.ActivityMainBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}