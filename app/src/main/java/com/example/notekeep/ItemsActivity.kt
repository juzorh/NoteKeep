package com.example.notekeep

import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeep.databinding.ActivityItemsBinding
import android.os.PowerManager

class ItemsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarItems.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_items)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.noteListFragment, R.id.nav_gallery,
                R.id.nav_course
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val wifi = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        wifi.isWifiEnabled = !wifi.isWifiEnabled

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_items)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /**
     * How to set NavigationView click listeners
     */
//    private fun navigationListener(nav: NavigationView){
//        nav.setNavigationItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.nav_note -> {
//                    handleNavSelection("Notes")
//                    true
//                }
//                R.id.nav_course -> {
//                    handleNavSelection("Courses")
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }
//    }
//
//    private fun handleNavSelection(text: String){
//        Toast.makeText(this, text,Toast.LENGTH_LONG).show()
//    }
}