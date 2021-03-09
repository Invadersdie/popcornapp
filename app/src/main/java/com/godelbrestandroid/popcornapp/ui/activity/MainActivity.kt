package com.godelbrestandroid.popcornapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.presentation.presenter.MainPresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.SearchMode
import com.godelbrestandroid.popcornapp.presentation.view.MainView
import com.godelbrestandroid.popcornapp.ui.fragment.SearchFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.HomeFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.movies.MoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.tvShows.TvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.people.PopularPeopleFragment
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.app_bar_main.*

private const val MainActivityTAG = "MainActivity"
private const val SearchFragmentTAG = "SearchFragment"
private const val LastScreen = "LastScreen"

class MainActivity : MvpAppCompatActivity(), MainView,
    NavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    lateinit var fragmentManager: FragmentManager
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var searchView: SearchView? = null
    private lateinit var searchViewQueryListener: SearchView.OnQueryTextListener

    private lateinit var searchMode: SearchMode


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        fragmentManager = supportFragmentManager

        if (savedInstanceState == null) {
            onNavigationItemSelected(navView.menu.getItem(0))
            navView.menu.getItem(0).isChecked = true
        }

        searchViewQueryListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                (fragmentManager.findFragmentByTag(SearchFragmentTAG) as? SearchFragment)?.applySearchQuery(
                    newText
                )
                return false
            }
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        menu.findItem(R.id.action_search).let(::onSearchViewCreated)
        return true
    }

    private fun onSearchViewCreated(searchMenuItem: MenuItem) {
        searchView = searchMenuItem.actionView as? SearchView
        searchView?.setOnSearchClickListener {
            val searchFragment = SearchFragment.newInstance(searchMode)
            replaceFragment(searchFragment, true, SearchFragmentTAG)
        }
        searchView?.setOnCloseListener {
            fragmentManager.popBackStack()
            false
        }
        searchView?.setOnQueryTextListener(searchViewQueryListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackTrace: Boolean, tag: String = "") {
        fragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_holder, fragment, tag)
            if (addToBackTrace) {
                addToBackStack(null)
            }
            commit()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        if (item.isChecked.not()) {
            replaceFragment(
                when (item.itemId) {
                    R.id.nav_home -> {
                        searchMode = SearchMode.ALL
                        HomeFragment()
                    }
                    R.id.nav_movies -> {
                        searchMode = SearchMode.MOVIE
                        MoviesFragment()
                    }
                    R.id.nav_tv_shows -> {
                        searchMode = SearchMode.TV_SHOWS
                        TvShowsFragment()
                    }
                    R.id.nav_people -> {
                        searchMode = SearchMode.PEOPLE
                        PopularPeopleFragment()
                    }
                    else -> throw IllegalStateException()
                }, false
            )
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun disableDrawerLayout() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toggle.isDrawerIndicatorEnabled = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.setToolbarNavigationClickListener { fragmentManager.popBackStack() }
    }

    fun enableDrawerLayout() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toggle.isDrawerIndicatorEnabled = true
        toggle.setToolbarNavigationClickListener { }
        searchView?.onActionViewCollapsed()
    }

    fun showLoading() {
        progress_bar.show()
    }

    fun hideLoading() {
        progress_bar.hide()
    }
}