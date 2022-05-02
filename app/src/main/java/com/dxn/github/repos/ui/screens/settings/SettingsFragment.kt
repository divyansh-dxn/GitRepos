package com.dxn.github.repos.ui.screens.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dxn.github.repos.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}