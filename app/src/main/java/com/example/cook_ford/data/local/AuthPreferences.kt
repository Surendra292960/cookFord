import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.cook_ford.utils.AppConstants.AUTH_KEY


class AuthPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken:String){
        dataStore.edit { pref ->
            pref[AUTH_KEY] = loginToken
        }
    }
    suspend fun saveAuthId(id:String){
        dataStore.edit { pref ->
            pref[AUTH_KEY] = id
        }
    }

}
