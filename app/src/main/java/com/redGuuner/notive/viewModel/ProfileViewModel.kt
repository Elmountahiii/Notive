package com.redGuuner.notive.viewModel

import androidx.lifecycle.ViewModel
import com.redGuuner.notive.repository.FirebaseRepository

class ProfileViewModel:ViewModel() {


    val userInformation by lazy {
        FirebaseRepository.userInformation
    }
    val gettingUserInformationException by lazy {
        FirebaseRepository.gettingUserInformationException
    }

    fun getUserInformation(){
        FirebaseRepository.getUserInformation()
    }


}