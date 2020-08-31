package com.redGuuner.notive.view.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redGuuner.notive.R
import com.redGuuner.notive.viewModel.PasswordViewModel
import com.redGuuner.notive.viewModel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_password.*

class PasswordFragment : Fragment(R.layout.fragment_password) {

    lateinit var viewModel: PasswordViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewModel()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        SendPasswordButton.setOnClickListener {
            //Do some Input validation
            viewModel.restPassword(PasswordRestEditText.text.toString())


        }
    }



   private fun setUpViewModel(){
        viewModel= ViewModelProvider(this).get(PasswordViewModel::class.java)
    }

    private fun setUpObservers() {
      viewModel.restPasswordResult.observe(viewLifecycleOwner, Observer { result ->
           showToast(result)
          viewModel.restPasswordResult.value=""

      })
    }

    private fun showToast( message: String) {
        if (!message.isBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }




}