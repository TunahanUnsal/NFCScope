package com.ezdream.nfc.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonProvider {

    var gson: Gson = GsonBuilder().serializeNulls().create()
}