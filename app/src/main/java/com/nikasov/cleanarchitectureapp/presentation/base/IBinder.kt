package com.nikasov.cleanarchitectureapp.presentation.base

interface IBinder<T> {
    fun bindView(model: T, position: Int)
}