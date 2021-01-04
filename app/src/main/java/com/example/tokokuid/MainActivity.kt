package com.example.tokokuid

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.tokokuid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.container.adapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.bottomNavi.setOnNavigationItemSelectedListener(this)
        binding.container.setCurrentItem(0,true)
        binding.container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> binding.bottomNavi.menu.findItem(R.id.home_menu).isChecked = true
                    1 -> binding.bottomNavi.menu.findItem(R.id.feed_menu).isChecked = true
                    2 -> binding.bottomNavi.menu.findItem(R.id.chatting_menu).isChecked = true
                    3 -> binding.bottomNavi.menu.findItem(R.id.account_menu).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_menu -> binding.container.setCurrentItem(0,true)
            R.id.feed_menu -> binding.container.setCurrentItem(1,true)
            R.id.chatting_menu -> binding.container.setCurrentItem(2,true)
            R.id.account_menu -> binding.container.setCurrentItem(3,true)
        }
        return true
    }
}