package com.redGuuner.notive.view.fragments

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redGuuner.notive.R
import com.redGuuner.notive.utils.User
import com.redGuuner.notive.viewModel.AddInformationViewModel
import kotlinx.android.synthetic.main.fragment_add_information.*

class AddInformationFragment : Fragment(R.layout.fragment_add_information) {

    lateinit var viewModel: AddInformationViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewModel()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        SaveButton.setOnClickListener {

            viewModel.saveUserInformation(User(
                FullName = FullName.text.toString(),
                Email = Email.text.toString(),
                Country = country.text.toString(),
                City = city.text.toString(),
                AddressOne = addressOne.text.toString(),
                AddressTow = addressTow.text.toString(),
                Zip = zip.text.toString()
            ))


        }
    }

    private fun setUpObservers() {
        viewModel.savingInformationResult.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                toProfile()
                viewModel.savingInformationResult.value=false
            }
        })
        viewModel.savingInformationException.observe(viewLifecycleOwner, Observer { exception ->
            showException(exception)
            viewModel.savingInformationException.value = ""
        })
    }

    private fun setUpViewModel() {

        viewModel = ViewModelProvider(this).get(AddInformationViewModel::class.java)

    }


    private fun showException(exception: String) {
        if (!exception.isBlank()) {
            Toast.makeText(this.context, exception, Toast.LENGTH_LONG).show()
        }
    }


    private fun toProfile(){
        findNavController().navigate(R.id.action_addInformationFragment_to_profileFragment2)

    }

}