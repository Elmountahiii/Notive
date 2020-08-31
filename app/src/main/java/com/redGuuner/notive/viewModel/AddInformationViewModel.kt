package com.redGuuner.notive.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redGuuner.notive.repository.FirebaseRepository
import com.redGuuner.notive.utils.User

class AddInformationViewModel: ViewModel() {

    val savingInformationResult by lazy {
        FirebaseRepository.savingInformationResult
    }
    val savingInformationException by lazy {
        FirebaseRepository.savingInformationException
    }



    fun saveUserInformation(user: User){

        FirebaseRepository.saveUserInformation(user)

    }

}