package com.example.picnat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.picnat.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        const val SCREEN_NAME = "MainActivity"
    }

    private lateinit var navController: NavController

    private fun showSnackbar(){
        val snack: Snackbar = Snackbar.make(viewSnack,getString(R.string.internet_connection),Snackbar.LENGTH_LONG)
        snack.view.animate()
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavController()
    }

    private val bottomNavigationView = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.home -> {
                if(navController.currentDestination?.id != R.id.homeFragment){
                    navController.navigate(R.id.action_global_homeFragment)
                }
                true
            }
            R.id.messages -> {
                if(navController.currentDestination?.id != R.id.messageFragment){
                    navController.navigate(R.id.action_global_messageFragment)
                }
                true
            }
            R.id.settings -> {
                if(navController.currentDestination?.id != R.id.settingsFragment){
                    navController.navigate(R.id.action_global_settingsFragment)
                }
                true
            }
            else -> true
        }
    }

    private fun setUpNavController(){
        navController = Navigation.findNavController(this,R.id.main_nav_host_fragment)
        setupBottomNavMenu(navController)

        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavigationView)
    }

    private fun setupBottomNavMenu(navController: NavController){
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }


}
