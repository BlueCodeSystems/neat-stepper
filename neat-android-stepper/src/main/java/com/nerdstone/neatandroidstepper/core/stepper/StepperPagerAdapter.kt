package com.nerdstone.neatandroidstepper.core.stepper

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class StepperPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var fragmentList: List<Step> = mutableListOf()
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val count: Int get() = fragmentList.size

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

    fun getStepAt(position: Int): Step = fragmentList[position]
}

private const val MIN_SCALE = 0.75f

/**
 * ViewPager2 version of the depth page transformer.
 * See https://developer.android.com/training/animation/screen-slide#depth-page
 */
class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> { // [-Infinity,-1)
                    alpha = 0f
                }
                position <= 0 -> { // [-1,0]
                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> { // (0,1]
                    alpha = 1 - position
                    translationX = pageWidth * -position
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - kotlin.math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}
