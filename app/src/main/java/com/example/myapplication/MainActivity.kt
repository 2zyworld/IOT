package com.example.myapplication

import android.Manifest
import android.os.Bundle
import android.system.Os.access
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Transformations.map
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding


import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_contents.*
import kotlinx.coroutines.NonCancellable.start
import java.nio.file.Files.find

lateinit var locationPermission: ActivityResultLauncher<Array<String>>

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    var mBackWait:Long = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainContents.toolbarv)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        binding.naviView.setNavigationItemSelectedListener(this)

        val drawerLayout: DrawerLayout = binding.layoutDrawer
        val naviView: NavigationView = binding.naviView
        val navbottomView: BottomNavigationView = findViewById(R.id.navView)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_calender,
                R.id.navigation_setting,
                R.id.navigation_dairy,
                R.id.navigation_graph,
                R.id.navigation_music,
                R.id.navigation_meditation,
                R.id.navigation_mypage,
                R.id.navigation_room,
                R.id.navigation_askhelp,
            ), drawerLayout
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
       naviView.setupWithNavController(navController)
       navbottomView.setupWithNavController(navController)

        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { results ->
            if(results.all{ it.value }) {
                start()
            } else {
                Toast.makeText(this,
                    "권한 승인이 필요합니다.",
                    Toast.LENGTH_LONG).show()
            }
        }
        locationPermission.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {

            R.id.action_search -> {return super.onOptionsItemSelected(item)}
            R.id.navigation_mypage -> Toast.makeText(applicationContext, "mypage", Toast.LENGTH_SHORT).show()
            R.id.navigation_music -> Toast.makeText(applicationContext, "노래듣기", Toast.LENGTH_SHORT).show()
            R.id.navigation_meditation -> Toast.makeText(applicationContext, "명상", Toast.LENGTH_SHORT).show()
        }
        binding.layoutDrawer.closeDrawers()
        return false

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           R.id.navi_view->{
                layout_drawer.openDrawer(GravityCompat.START) }
//        R.id.home -> {
//                   finish()
//                   return true
//            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show()
        } else {
            finishAffinity()
            System.runFinalization()
            System.exit(0) //액티비티 종료

        }
    }



    }

