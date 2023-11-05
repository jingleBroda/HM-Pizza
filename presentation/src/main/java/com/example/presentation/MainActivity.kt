package com.example.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.contract.InternetConnection
import com.example.presentation.contract.NetworkConnectivityChecker
import com.example.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), InternetConnection {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var networkConnectivityChecker: NetworkConnectivityChecker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        networkConnectivityChecker = NetworkConnectivityChecker(this)
        navController =
            (supportFragmentManager.findFragmentById(R.id.FragmentLayout) as NavHostFragment).navController

        with(binding) {
            bottomNavigationView.setupWithNavController(navController)
        }

        setContentView(binding.root)
    }

    override fun isInternetConnected(): Boolean = networkConnectivityChecker.isInternetConnected()
}