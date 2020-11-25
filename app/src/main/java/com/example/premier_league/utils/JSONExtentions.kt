package com.example.premier_league.utils

import org.json.JSONArray
import org.json.JSONObject

inline fun <R> JSONArray.map(transform : (JSONObject) -> R?) : List<R> =
    mutableListOf<R>().also { list ->
        repeat(length()){
            (get(it) as? JSONObject)?.let(transform)?.let(list::add)
        }
    }
