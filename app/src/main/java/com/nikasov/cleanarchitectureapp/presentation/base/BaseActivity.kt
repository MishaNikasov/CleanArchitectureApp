package com.nikasov.cleanarchitectureapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class BaseActivity<ActivityBinding : ViewBinding> : AppCompatActivity() {
    lateinit var binding: ActivityBinding
}