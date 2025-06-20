package com.shiv.instacompose.data.filereader
import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import java.lang.reflect.Type

class ResourceJsonProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : JsonProvider {

    override fun <T> fromFile(fileName: String, clazz: Type): T {
        val json = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }

        return Gson().fromJson(json, clazz)
    }
}

interface JsonProvider{
    fun <T> fromFile(fileName: String, clazz: Type): T
}