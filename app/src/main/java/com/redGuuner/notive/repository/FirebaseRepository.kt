package com.redGuuner.notive.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.redGuuner.notive.utils.User

object FirebaseRepository {

    private val authenticator = FirebaseAuth.getInstance()
    private val firebaseUser = MutableLiveData<FirebaseUser>()
    private val firebaseDatabase = FirebaseFirestore.getInstance()
    private const val collectionsPath = "Users Information's"

    val logInResult = MutableLiveData<Boolean>()
    val loginException = MutableLiveData<String>()
    val signInResult = MutableLiveData<Boolean>()
    val signInException = MutableLiveData<String>()
    val socialMediaResult = MutableLiveData<Boolean>()
    val socialMediaException = MutableLiveData<String>()
    val restPasswordResult = MutableLiveData<String>()
    val savingInformationResult = MutableLiveData<Boolean>()
    val savingInformationException = MutableLiveData<String>()
    val userInformation = MutableLiveData<User>()
    val gettingUserInformationException=MutableLiveData<String>()


    fun logIn(Email: String, Password: String) {

        authenticator.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    logInResult.value = true
                    firebaseUser.value = authenticator.currentUser
                }

            }
            .addOnFailureListener { exception ->
                loginException.value = exception.message


            }
    }

    fun signIn(Email: String, Password: String) {

        authenticator.createUserWithEmailAndPassword(Email, Password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {

                    signInResult.value = true
                    firebaseUser.value = authenticator.currentUser

                }

            }.addOnFailureListener { exception ->
                signInException.value = exception.message

            }

    }

    fun resatPassword(email: String) {
        authenticator
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    restPasswordResult.value = "We Sent You a Link Please Check Your Email"
                } else if (result.isCanceled) {
                    restPasswordResult.value = "Canceled"

                }

            }
            .addOnFailureListener { exception ->
                restPasswordResult.value = exception.message

            }
    }

    fun checkUser(): Boolean {
        return authenticator.currentUser != null
    }

    fun facebookAuthentication(FacebookAccessToken: AccessToken) {

        val authCredential = FacebookAuthProvider.getCredential(FacebookAccessToken.token)

        authentic(authCredential)

    }

    fun googleAuthentication(GoogleAccount: GoogleSignInAccount) {
        val authCredential = GoogleAuthProvider.getCredential(GoogleAccount.idToken, null)
        authentic(authCredential)


    }

    fun saveUserInformation(user: User) {
        firebaseDatabase
            .collection(collectionsPath)
            .document(authenticator.currentUser!!.uid)
            .set(user)
            .addOnSuccessListener {

                savingInformationResult.value = true

            }
            .addOnFailureListener { exception ->
                savingInformationException.value = exception.message

            }
    }

    fun getUserInformation() {
        firebaseDatabase
            .collection(collectionsPath)
            .document(authenticator.currentUser!!.uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot.exists()) {

                    userInformation.value=documentSnapshot.toObject(User::class.java)

                }


            }
            .addOnFailureListener {exception ->
                gettingUserInformationException.value=exception.message
            }

    }

    private fun authentic(authCredential: AuthCredential) {
        authenticator.signInWithCredential(authCredential)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    socialMediaResult.value = true
                    firebaseUser.value = authenticator.currentUser


                } else if (result.isCanceled) {
                    socialMediaException.value = "Cancel"
                }

            }
            .addOnFailureListener { exception ->
                socialMediaException.value = exception.message
            }

    }


}