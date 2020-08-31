package com.redGuuner.notive.viewModel

import androidx.lifecycle.ViewModel
import com.redGuuner.notive.repository.FirebaseRepository

class SignInViewModel : ViewModel() {

    val signInResult by lazy {
        FirebaseRepository.signInResult
    }
    val signInException by lazy {
        FirebaseRepository.signInException
    }

    fun signIn(Email:String,Password:String){
        FirebaseRepository.signIn(Email,Password)
    }
}