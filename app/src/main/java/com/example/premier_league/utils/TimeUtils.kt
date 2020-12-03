package com.example.premier_league.utils

import android.app.DatePickerDialog
import android.content.Context
import com.example.premier_league.R
import com.example.premier_league.base.BaseView
import com.example.premier_league.data.model.Time
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils : BaseView {

    private const val ONE = 1
    private const val MONTH_IN_YEAR = 12
    private const val FORMAT_DATE_TO_GET = "yyyy-MM-dd"

    fun getTime(distanceFrom: Int, distanceTo: Int): Time? {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = ((currentDate.get(Calendar.MONTH) + ONE))
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val dayFrom = day - distanceFrom
        val dayTo = day + distanceTo

        val fakeDateFrom = "$year-$month-$dayFrom"
        val fakeDateTo = "$year-$month-$dayTo"

        try {
            val dateFrom =
                SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).parse(fakeDateFrom)
            val dateTo = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).parse(fakeDateTo)
            val from = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).format(dateFrom)
            val to = SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).format(dateTo)
            return Time(from, to)
        } catch (e: Exception) {
            showMessage(e.toString())
        }
        return null
    }

    fun getDatePickerDialog(context: Context, getData: (Time) -> Unit) {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            context,
            { _, yearPick, monthPick, dayPick ->
                val fakeDate = "$yearPick-${(monthPick + ONE) % MONTH_IN_YEAR}-$dayPick"
                val parseDate =
                    SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).parse(fakeDate)
                val date =
                    SimpleDateFormat(FORMAT_DATE_TO_GET, Locale.getDefault()).format(parseDate)
                getData(Time(date, date))
            }, year, month, day
        )
        datePickerDialog.setTitle(context.getString(R.string.title_select_date))
        datePickerDialog.show()
    }

    fun convertDateToMilSeconds(myDate: String?): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        var date: Date? = null
        try {
            date = sdf.parse(myDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date!!.time
    }
}
