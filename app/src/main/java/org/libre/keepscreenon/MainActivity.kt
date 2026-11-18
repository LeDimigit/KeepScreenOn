package org.libre.keepscreenon

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

/**
 * Aucune interface : à chaque lancement, l'appli bascule l'état puis se ferme.
 *  - Activation : le délai de mise en veille système est porté au maximum (jamais de veille).
 *  - Lancement suivant : le délai d'origine est rétabli.
 * Nécessite l'autorisation « Modifier les paramètres système » (WRITE_SETTINGS).
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Settings.System.canWrite(this)) {
            Toast.makeText(this, R.string.toast_permission, Toast.LENGTH_LONG).show()
            startActivity(
                Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:$packageName"))
            )
            finish()
            return
        }

        val resolver = contentResolver
        if (Prefs.isActive(this)) {
            Settings.System.putInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, Prefs.savedTimeout(this))
            Prefs.setActive(this, false)
            Toast.makeText(this, R.string.toast_off, Toast.LENGTH_SHORT).show()
        } else {
            val current = Settings.System.getInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, 60_000)
            Prefs.setSavedTimeout(this, current)
            Settings.System.putInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, Int.MAX_VALUE)
            Prefs.setActive(this, true)
            Toast.makeText(this, R.string.toast_on, Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
