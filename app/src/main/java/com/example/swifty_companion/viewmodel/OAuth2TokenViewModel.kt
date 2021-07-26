package com.example.swifty_companion.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.swifty_companion.network.ClientCredentialsDTO
import com.example.swifty_companion.network.UserInfoDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class OAuth2TokenViewModel: ViewModel() {

    var client: HttpClient? = null
    var clientCredentials: ClientCredentialsDTO? = null
    val jsonFormat = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    init {
        initClient()

/*//        Log.e("OAUTH2 USER INFO", format.encodeToString(getUserInfo("dmortal")))
        getUserInfo("dmortal")?.let { longLog(format.encodeToString(it)) }
        runBlocking {
            delay(1000L * 1L)
        }
        Log.e("OAUTH2 CLIENT", format.encodeToString(clientCredentials))
        runBlocking {
            delay(1000L * 1L)
        }
        try {
            val id = getUserInfo("NOTEXISTLOGINFORSURE")
        }
        catch (exception: ResponseException) {
            Log.e("TEST", "id = ${exception.response.status}")

        }
//        Log.e("OAUTH2 USER INFO", format.encodeToString(getUserInfo("NOTEXISTLOGINFORSURE")))
//        Log.e("OAUTH2 CLIENT", format.encodeToString(clientCredentials))*/
    }

    fun longLog(string: String) {
        if (string.length > 1000) {
            Log.e("OAUTH2", string.substring(0, 1000));
            longLog(string.substring(1000));
        }
        else
            Log.e("OAUTH2", string);
    }

    fun getUserInfo(login: String): UserInfoDTO? = run {
        runBlocking {
            client
                ?.get<HttpResponse>("https://api.intra.42.fr/v2/users/$login")
                ?.receive()
        }
    }

    fun initClient() {
        client = HttpClient(OkHttp) {
            install(Auth) {
                bearer {
                    loadTokens {
                        fetchBearerToken()
                        clientCredentials?.accessToken?.let {
                            BearerTokens(it, it) }
                    }
                    refreshTokens {
                        fetchBearerToken()
                        clientCredentials?.accessToken?.let {
                            BearerTokens(it, it) }
                    }
                }
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = jsonFormat)
            }
        }
    }
    fun fetchBearerToken() {
        Log.e("OAUTH2", "FETCH TOKENS")
        clientCredentials = getClientCredentials(client)
    }

    fun getClientCredentials(client: HttpClient?): ClientCredentialsDTO? = run {
        runBlocking {
            client?.post<HttpResponse>("https://api.intra.42.fr/oauth/token") {
                parameter(
                    "client_id",
                    "9e84538b3c4f1f3541b87544146f82a3a5e66ebc5cc542e46b976483c4d4f9d2"
                )
                parameter(
                    "client_secret",
                    "a46d130690e2832ca4546b783dbdfe5936d7f28de7ee490467bb76d8d4022c62"
                )
                parameter(
                    "grant_type",
                    "client_credentials"
                )
            }?.receive<ClientCredentialsDTO>()
        }
    }

}