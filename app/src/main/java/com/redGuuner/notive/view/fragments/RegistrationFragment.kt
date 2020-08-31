package com.redGuuner.notive.view.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.tabs.TabLayoutMediator
import com.redGuuner.notive.R
import com.redGuuner.notive.adapter.RegistrationStateAdapter
import com.redGuuner.notive.viewModel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    lateinit var viewModel: RegistrationViewModel
    private lateinit var facebookCallback: CallbackManager
    private lateinit var googleSinInClient: GoogleSignInClient
    private val googleSingInCode = 1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpTapLayout()
        setUpViewModel()
        setUpObservers()
        checkUser()
        setUpFacebookCallback()
        setUpGoogle()
    }

    override fun onResume() {
        super.onResume()
        GoogleLogIn.setOnClickListener {
            val singInIntent = googleSinInClient.signInIntent
            startActivityForResult(singInIntent, googleSingInCode)

        }

        FacebookLogIn.setOnClickListener {
            FacebookButton.performClick()
        }
    }

    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == googleSingInCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {


                val account = task.getResult(ApiException::class.java)

                account?.let {

                    viewModel.useGoogle(account)

                }

            } catch (e: ApiException) {

            }


        } else {


            facebookCallback.onActivityResult(requestCode, resultCode, data)
        }

    }

    //This Is For ViewPager And TabLayout
    private fun setUpTapLayout() {
        ViewPager.adapter = RegistrationStateAdapter(this)
        TabLayoutMediator(TabLayout, ViewPager) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "SignIn"
                }
                1 -> {
                    tab.text = "LogIn"
                }
            }

        }.attach()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    private fun setUpObservers() {
        viewModel.socialMediaResult.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                toProfile()
            }

        })

        viewModel.socialMediaException.observe(viewLifecycleOwner, Observer { exepation ->
            showToast(exepation)
        })
    }

    private fun checkUser() {
        if (viewModel.checkUser()) {

            toProfile()
        }
    }

    private fun setUpFacebookCallback() {
        facebookCallback = CallbackManager.Factory.create()
        FacebookButton.setPermissions("email")
        FacebookButton.fragment = this
        FacebookButton.registerCallback(facebookCallback,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {

                    loginResult?.accessToken?.let { accessToken ->
                        viewModel.useFacebook(accessToken)
                    }

                }

                override fun onCancel() {
                    showToast("Cancel")

                }

                override fun onError(exception: FacebookException) {

                    exception.message?.let { message ->
                        showToast(message)
                    }


                }

            })


    }

    private fun setUpGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSinInClient = GoogleSignIn.getClient(requireView().context, googleSignInOptions)

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun toProfile() {
        findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)

    }
}