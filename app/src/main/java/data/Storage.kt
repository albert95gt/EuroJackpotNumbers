package data

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import androidx.datastore.preferences.core.edit

// 1️⃣ DataStore definíció a Context-hez
val Context.dataStore by preferencesDataStore(name = "eurojackpot")

// 2️⃣ Kulcs a mentett adathoz
val LAST_NUMBERS_KEY = stringPreferencesKey("last_numbers")

// 3️⃣ Adatok mentése
suspend fun saveLastNumbers(context: Context, value: String) {
    context.dataStore.edit { preferences ->
        preferences[LAST_NUMBERS_KEY] = value
    }
}

// 4️⃣ Adatok betöltése
suspend fun loadLastNumbers(context: Context): String? {
    val preferences = context.dataStore.data.first()
    return preferences[LAST_NUMBERS_KEY]
}