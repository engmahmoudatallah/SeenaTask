package com.seenatask.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.seenatask.R

/**
 * This class use to inheritance purpose
 *
 * @author Mahmoud Atallah
 * @version 1.2
 * @implNote Any activity creating in this project...must by extends from this class
 */

abstract class BaseActivity : AppCompatActivity() {

    protected var recyclerView: RecyclerView? = null
    protected var mSavedInstanceState: Bundle? = null
    protected lateinit var mContext: Context
    protected lateinit var mActivity: Activity
    protected var toolbar: Toolbar? = null
    protected var title: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRoot())

        mContext = this
        mActivity = this
        mSavedInstanceState = savedInstanceState

        if (isToolBarEnabled()) configureToolbar()

        if (isRecyclerViewEnabled()) configRecycle()



        initializeComponents()
    }

    open fun goToActivity(cls: Class<*>?) {
        val mIntent = Intent(mActivity, cls)
        mActivity.startActivity(mIntent)
    }


    private fun configRecycle() {
        recyclerView = findViewById(R.id.recyclerview_base)
        recyclerView?.setHasFixedSize(true)
    }


    @SuppressLint("SetTextI18n")
    private fun configureToolbar() {
        toolbar = findViewById(R.id.toolbar)
        title = findViewById(R.id.toolbar_title)
        toolbar?.visibility = View.VISIBLE
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar?.setTitleTextColor(ContextCompat.getColor(mContext, R.color.white))
        // check if enable back
        if (isEnableBack()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.setNavigationIcon(R.drawable.ic_arrow)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()

        }


        return super.onOptionsItemSelected(item)
    }

    /**
     * for support onCreate method for all subClasses
     */
    protected abstract fun initializeComponents()


    /**
     * used with ViewBinding to return binding.getRoot() from subClasses
     *
     * @return the ViewBinding root
     */
    protected abstract fun getLayoutRoot(): View?

    /**
     * @return true if you need support toolbar in subClass or return false if not
     */
    protected abstract fun isToolBarEnabled(): Boolean

    /**
     * @return true if you need support back in toolbar in subClass or return false if not
     */
    protected abstract fun isEnableBack(): Boolean

    /**
     * @return true if you need support bottomBar in subClass or return false if not
     */
    protected abstract fun isBottomNavBarEnabled(): Boolean

    /**
     * @return true if you need support recyclerView in subClass or return false if not
     */
    protected abstract fun isRecyclerViewEnabled(): Boolean


}