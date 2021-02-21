package com.example.picnat

import androidx.appcompat.app.AppCompatActivity


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
            R.id.map -> {
                if(navController.currentDestination?.id != R.id.mapFragment){
                    navController.navigate(R.id.action_global_mapFragment)
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
