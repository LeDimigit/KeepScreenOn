package org.libre.keepscreenon

import android.content.Context

object Prefs {
    private fun sp(c: Context) = c.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    fun isActive(c: Context) = sp(c).getBoolean("active", false)
    fun setActive(c: Context, v: Boolean) = sp(c).edit().putBoolean("active", v).apply()
    /** Délai de mise en veille d'origine (ms), restauré à la désactivation. */
    fun savedTimeout(c: Context) = sp(c).getInt("saved_timeout", 60_000)
    fun setSavedTimeout(c: Context, v: Int) = sp(c).edit().putInt("saved_timeout", v).apply()
}
