package com.redGuuner.notive.view.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redGuuner.notive.R
import com.redGuuner.notive.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    lateinit var viewModel: ProfileViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewModel()
        setUpObservers()

    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserInformation()

    }

    override fun onResume() {
        super.onResume()
        editInformationButton.setOnClickListener {
            toAddInformation()
        }
    }

    private fun setUpObservers() {
        viewModel.userInformation.observe(viewLifecycleOwner, Observer { user ->
            userInformationText.text = user.printAllInformation()
        })

        viewModel.gettingUserInformationException.observe(viewLifecycleOwner, Observer { Exception ->
            showToast(Exception)
            viewModel.gettingUserInformationException.value=""
        })

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

    }

    private fun toAddInformation() {
        findNavController().navigate(R.id.action_profileFragment_to_addInformationFragment)

    }
    private fun showToast( message: String) {
        if (!message.isBlank()){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()

        }

    }
}