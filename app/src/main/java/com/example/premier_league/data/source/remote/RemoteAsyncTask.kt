package com.example.premier_league.data.source.remote

import android.os.AsyncTask
import com.example.premier_league.R
import java.lang.Exception

class RemoteAsyncTask<T>(
    private val callback: OnDataLoadedCallback<T>,
    private val handle: (String) -> T
) : AsyncTask<String, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String): T? =
        try {
            handle(params[0])
                ?: throw Exception(R.string.err_fist_param_not_be_null.toString())
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: (callback.onFailure(exception))
    }
}
