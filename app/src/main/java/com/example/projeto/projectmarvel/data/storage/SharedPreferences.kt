package com.example.projeto.projectmarvel.data.storage

import android.content.Context
import android.preference.PreferenceManager
import com.example.projeto.projectmarvel.data.model.SavedCharacter
import com.example.projeto.projectmarvel.data.model.Thumbnail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPreferences (context: Context?) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }

    fun getList(): ArrayList<SavedCharacter> {
        val gson = Gson()
        val json: String? = preferences.getString(CHARACTERS, null)
        val type: Type = object : TypeToken<ArrayList<SavedCharacter?>?>() {}.type

        if (json == null) {
            return arrayListOf()
        }

        return gson.fromJson<ArrayList<SavedCharacter>>(json, type)
    }

    fun compare(
        id :Int,
        name : String,
        thumbnail: Thumbnail
    ): Boolean {
        val list: MutableList<SavedCharacter> = getList()
        var test = 0

        list.forEach{
            if (it.id == id) {
                test = 1
            }  else {
                test = 0
            }
        }
        when (test) {
            1 -> {
                deleteMovie(id)
                saveMovie(
                    id,
                    name ,
                    thumbnail
                )
            }
            0 -> {
                saveMovie(
                    id,
                    name ,
                    thumbnail
                )
            }
            2 -> {
                return false
            }
        }
        return true
    }

    fun saveMovie(
        id :Int,
        name : String,
        thumbnail: Thumbnail
    ) {
        val list: MutableList<SavedCharacter> = getList()
        list.add(
            SavedCharacter(
                id,
                name ,
                thumbnail
            )
        )
        val editor: android.content.SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(CHARACTERS, json)
        editor.apply()
    }

    fun deleteMovie(selectedCharacter: Int) {
        val mutableList: MutableList<SavedCharacter> = getList()
        val list = getList()
        list.forEach{
            if (it.id == selectedCharacter) {
                mutableList.remove(it)
                //list.remove(it)
            }
        }

        val editor: android.content.SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mutableList)
        editor.putString(CHARACTERS, json)
        editor.apply()
    }

    companion object {
        const val CHARACTERS = "characters"

    }
}