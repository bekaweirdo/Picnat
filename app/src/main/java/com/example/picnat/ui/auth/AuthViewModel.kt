package com.example.picnat.ui.auth


import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.picnat.repository.AuthInterface
import com.example.picnat.repository.UserRepository
import com.example.picnat.ui.login.LoginActivity
import com.example.picnat.ui.register.RegisterActivity
import com.google.firebase.auth.FirebaseUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel (private val repository: UserRepository): ViewModel() {
    var email: String? = null
    var password: String? = null

    var authListener: AuthInterface? = null

    private val disposables = CompositeDisposable()


    fun login(){

        if(email.isNullOrEmpty() || email.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }

        authListener?.onStarted()

        val disposable = repository.login(email!!,password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }

    fun singup(){
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Please input all values")
            return
        }
        authListener?.onStarted()
        val disposable = repository.register(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            },{
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)
    }


    fun goToSignup(view: View){
        Intent(view.context,RegisterActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view:View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun getCurrentUser(): LiveData<FirebaseUser>{
        return repository.currentUser()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}