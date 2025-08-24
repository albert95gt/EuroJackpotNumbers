package data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

//1️⃣ Kulcs definiálása
val Context.dataStore by preferencesDataStore(name = "eurojackpot")
val NUMBERS_LIST_KEY = stringSetPreferencesKey("numbers_list")
val SPORTKA_LIST_KEY = stringSetPreferencesKey("sportka_numbers")


//2️⃣ Mentés
suspend fun saveNumbers(context: Context, value: String) {
    context.dataStore.edit { preferences ->
        val currentSet = preferences[NUMBERS_LIST_KEY] ?: emptySet()
        preferences[NUMBERS_LIST_KEY] = currentSet + value
    }
}

suspend fun saveSportkaNumbers(context: Context, value: String) {
    context.dataStore.edit { preferences ->
        val currentSet = preferences[SPORTKA_LIST_KEY] ?: emptySet()
        preferences[SPORTKA_LIST_KEY] = currentSet + value
    }
}

//3️⃣ Betöltés
suspend fun loadNumbers(context: Context): List<String> {
    val preferences = context.dataStore.data.first()
    return preferences[NUMBERS_LIST_KEY]?.toList() ?: emptyList()
}

suspend fun loadSportkaNumbers(context: Context): List<String> {
    val preferences = context.dataStore.data.first()
    return preferences[SPORTKA_LIST_KEY]?.toList() ?: emptyList()
}


