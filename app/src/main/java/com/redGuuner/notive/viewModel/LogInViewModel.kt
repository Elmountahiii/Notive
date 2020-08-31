package com.redGuuner.notive.viewModel

import androidx.lifecycle.ViewModel
import com.redGuuner.notive.repository.FirebaseRepository

class LogInViewModel : ViewModel() {


    val loginResult by lazy {
        FirebaseRepository.logInResult
    }
    val loginException by lazy {
        FirebaseRepository.loginException
    }


    fun logIn(Email:String,Password:String){
        FirebaseRepository.logIn(Email,Password)
    }

}