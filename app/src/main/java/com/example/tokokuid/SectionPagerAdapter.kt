package com.example.tokokuid

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.home_fragment, R.string.feed_fragment,R.string.chatting_fragment,R.string.profile_fragment)
    override fun getItem(position: Int): Fragment {
        var fragment:Fragment = HomeFragment()
        when(position){
            0 -> fragment = HomeFragment()
            1 -> fragment = FeedFragment()
            2 -> fragment = ChattingFragment()
            3 -> fragment = ProfileFragment()
        }
        return fragment
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}