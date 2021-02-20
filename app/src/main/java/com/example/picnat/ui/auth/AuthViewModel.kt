package com.example.picnat.ui.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picnat.data.go
import com.example.picnat.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel (private val repository: UserRepository): ViewModel() {
    var email: String? = null
    var password: String? = null
    //var authListener: AuthInterface? = null

    fun login(){

        if(email.isNullOrEmpty() || email.isNullOrEmpty()){
            //authListener?.onFailure("Invalid email or password")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.login(email!!,password!!).go(
                success = {},
                failure = {}
            )
        }
    }

    fun singup(){
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //authListener?.onFailure("Please input all values")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(email!!,password!!).go(
                success = {},
                failure = {}
            )
        }
    }


//    fun goToSignup(view: View){
//        Intent(view.context,RegisterActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }
//
//    fun goToLogin(view:View){
//        Intent(view.context,LoginActivity::class.java).also {
//            view.context.startActivity(it)
//        }
//    }

    fun getCurrentUser(): LiveData<FirebaseUser>{
        return repository.currentUser()
    }
    fun logout(){
        repository.logout()
    }
}