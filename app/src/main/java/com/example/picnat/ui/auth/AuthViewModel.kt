package com.example.picnat.ui.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picnat.data.go
import com.example.picnat.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    fun login(email : String, password : String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(email, password).go(
                success = {
                    print(it)
                },
                failure = {
                    print(it)
                }
            )
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(email, password).go(
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

    fun getCurrentUser(): LiveData<FirebaseUser> {
        return repository.currentUser()
    }

    fun logout() {
        repository.logout()
    }
}