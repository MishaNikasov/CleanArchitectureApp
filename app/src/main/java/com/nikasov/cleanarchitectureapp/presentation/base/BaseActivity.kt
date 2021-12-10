package com.nikasov.cleanarchitectureapp.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class BaseActivity<ActivityBinding : ViewBinding> (
    private val binder: (LayoutInflater) -> ActivityBinding
) : AppCompatActivity() {

    private var binding: ActivityBinding? = null

    protected fun requireBinding(): ActivityBinding {
        return checkNotNull(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = binder(layoutInflater)
        this.binding = binding
        setContentView(binding.root)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}