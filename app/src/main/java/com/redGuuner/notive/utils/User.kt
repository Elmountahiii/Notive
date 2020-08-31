package com.redGuuner.notive.utils

data class User( var FullName: String="",
                 var Email: String="",
                 var Country: String="",
                 var City: String="",
                 var AddressOne: String="",
                 var AddressTow: String = "",
                 var Zip: String="") {



    fun printAllInformation(): String {
        return "FullName : $FullName ,\n" +
                "Email : $Email ,\n" +
                "Country : $Country ,\n" +
                "City : $City ,\n" +
                "Address 1 : $AddressOne,\n" +
                "Address 2 : $AddressTow,\n" +
                "Zip : $Zip ."
    }
}