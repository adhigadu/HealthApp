package aditya.sensyne.hospitaluk.ui

import aditya.sensyne.hospitaluk.R
import aditya.sensyne.hospitaluk.data.model.FilterBy
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private var sharedMainViewModel: SharedMainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupNavigation()
        setupViewModel()
        isStoragePermissionGranted()
    }

    private fun setupViewModel() {
        try {
            val viewModelProvider = ViewModelProvider(
                navController.getViewModelStoreOwner(R.id.nav_graph),
                ViewModelProvider.AndroidViewModelFactory(application)
            )
            sharedMainViewModel = viewModelProvider.get(SharedMainViewModel::class.java)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nhs_filter -> sharedMainViewModel?.getHospitalList(FilterBy.NHSHospital)
            R.id.independent_filter -> sharedMainViewModel?.getHospitalList(FilterBy.IndependentHospital)
            R.id.show_all_filter -> sharedMainViewModel?.getHospitalList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigation() {
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(toolbar, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.listFragment) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.GONE
            }
        }
    }

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else {
            true
        }
    }
}
