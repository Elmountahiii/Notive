package com.redGuuner.notive.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.redGuuner.notive.repository.FirebaseRepository

class RegistrationViewModel:ViewModel() {

    val socialMediaResult by lazy{
        FirebaseRepository.socialMediaResult
    }

    val socialMediaException by lazy{
        FirebaseRepository.socialMediaException
    }

    fun checkUser():Boolean{
        return FirebaseRepository.checkUser()
    }

    fun useFacebook(FacebookAccessToken: AccessToken)
    {
        FirebaseRepository.facebookAuthentication(FacebookAccessToken)
    }

    fun useGoogle(GoogleAccount: GoogleSignInAccount)
    {

        FirebaseRepository.googleAuthentication(GoogleAccount)

    }

}