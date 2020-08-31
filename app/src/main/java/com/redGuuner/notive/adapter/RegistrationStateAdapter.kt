package com.redGuuner.notive.adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.redGuuner.notive.view.fragments.LogInFragment
import com.redGuuner.notive.view.fragments.RegistrationFragment
import com.redGuuner.notive.view.fragments.SignInFragment

class RegistrationStateAdapter(fragmentActivity: RegistrationFragment) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SignInFragment()
            else ->LogInFragment()

        }
    }
}