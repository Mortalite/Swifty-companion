package com.example.swifty_companion

import android.widget.ImageView
import coil.Coil.enqueue
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.serialization.json.Json
import java.text.NumberFormat
import java.util.*

class Utils {

    companion object {

        val jsonFormat = Json {
            isLenient = true
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        fun getFormatLevel(level: String): String {
            val nf = NumberFormat.getInstance(Locale.ENGLISH)
            nf.maximumFractionDigits = 3

            return "${nf.format(level.toDouble())} lvl"
        }

        fun ImageView.loadUrl(url: String) {
            val request = ImageRequest.Builder(context)
                .data(url)
                .target(this)
                .build()

            enqueue(request)
        }

    }

}