package com.redGuuner.notive.viewModel

import androidx.lifecycle.ViewModel
import com.redGuuner.notive.repository.FirebaseRepository

class PasswordViewModel:ViewModel() {

    val restPasswordResult by lazy {
        FirebaseRepository.restPasswordResult
    }


    fun restPassword(Email:String){
        FirebaseRepository.resatPassword(Email)
    }
}