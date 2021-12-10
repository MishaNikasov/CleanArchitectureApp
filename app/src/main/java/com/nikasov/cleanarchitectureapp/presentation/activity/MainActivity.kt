package com.nikasov.cleanarchitectureapp.presentation.activity

import com.nikasov.cleanarchitectureapp.databinding.ActivityMainBinding
import com.nikasov.cleanarchitectureapp.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate)