package com.github.skyfe79.materialcollapsingtoolbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isShow = false
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            Snackbar.make(it, "Hello I'm SnackBar", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show()
        }

        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            var scrollRange = -1

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }

            if (scrollRange + verticalOffset == 0) {
                isShow = true
                showOption(R.id.action_info)
            } else if (isShow) {
                isShow = false
                hideOption(R.id.action_info)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        hideOption(R.id.action_info)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> return true
            R.id.action_info -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideOption(id: Int) {
        menu.findItem(id).isVisible = false
    }

    private fun showOption(id: Int) {
        menu.findItem(id).isVisible = true
    }
}
