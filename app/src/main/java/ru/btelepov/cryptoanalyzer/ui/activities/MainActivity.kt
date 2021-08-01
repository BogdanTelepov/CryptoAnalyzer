package ru.btelepov.cryptoanalyzer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import ru.btelepov.cryptoanalyzer.R
import ru.btelepov.cryptoanalyzer.databinding.ActivityMainBinding
import ru.btelepov.cryptoanalyzer.extensions.setupWithNavController
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavBar()
        }


    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavBar()
    }


    private fun setupBottomNavBar() {
        val bottomNavView = binding.bottomNavigationView
        val navGraphs = listOf(
            R.navigation.graph_home,
          
        )

        val controller = bottomNavView.setupWithNavController(
            navGraphs,
            supportFragmentManager,
            R.id.fragmentContainerViewMain,
            intent
        )
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true)
            super.onBackPressed()
    }
}