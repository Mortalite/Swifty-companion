package com.example.swifty_companion

import android.accounts.AccountManager
import androidx.lifecycle.ViewModel
import java.net.HttpURLConnection
import java.net.URL

class ConnectionViewModel : ViewModel() {

    var conn: HttpURLConnection? = null

    init {



/*        val url = URL("https://api.intra.42.fr")
        val conn = url.openConnection() as HttpURLConnection
        conn.apply {
            addRequestProperty("client_id", "9e84538b3c4f1f3541b87544146f82a3a5e66ebc5cc542e46b976483c4d4f9d2")
            addRequestProperty("client_secret", "a46d130690e2832ca4546b783dbdfe5936d7f28de7ee490467bb76d8d4022c62")
            setRequestProperty("Authorization", "OAuth $token")
        }*/
    }

}