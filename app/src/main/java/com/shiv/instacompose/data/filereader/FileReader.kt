package com.shiv.instacompose.data.filereader
import com.google.gson.Gson
import jakarta.inject.Inject
import java.io.FileNotFoundException
import java.lang.reflect.Type


//inline fun <reified T>Context.getJsonFromAssets(fileName: String): T {
//    val json = assets.open(fileName).bufferedReader().use { it.readText() }
//    return Gson().fromJson(json, T::class.java)
//}

//inline fun <reified T> getJsonFromResources(fileName: String): T {
//    val inputStream = object {}.javaClass.classLoader!!.getResourceAsStream(fileName)
//        ?: throw FileNotFoundException("File $fileName not found")
//    val json = inputStream.bufferedReader().use { it.readText() }
//    return Gson().fromJson(json, T::class.java)
//}

class ResourceJsonProvider @Inject constructor() : JsonProvider {
    override fun <T> fromFile(fileName: String, clazz: Type): T {
        val json = javaClass.classLoader!!
            .getResourceAsStream(fileName)?.bufferedReader()?.readText()
            ?: throw FileNotFoundException("File not found: $fileName")
        return Gson().fromJson(json, clazz)
    }
}

interface JsonProvider{
    fun <T> fromFile(fileName: String, clazz: Type): T
}