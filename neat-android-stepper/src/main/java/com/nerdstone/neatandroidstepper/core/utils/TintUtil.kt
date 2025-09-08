package com.nerdstone.neatandroidstepper.core.utils

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat

object TintUtil {

    /**
     * Tints a drawable with the color that is provided using DrawableCompat
     * to avoid deprecated setColorFilter(int, PorterDuff.Mode) API.
     */
    fun tintDrawable(drawable: Drawable?, @ColorInt color: Int): Drawable? {
        if (drawable == null) return null
        val wrapped = DrawableCompat.wrap(drawable).mutate()
        DrawableCompat.setTint(wrapped, color)
        DrawableCompat.setTintMode(wrapped, PorterDuff.Mode.SRC_IN)
        return wrapped
    }
}
