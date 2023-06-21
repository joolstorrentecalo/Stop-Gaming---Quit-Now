package com.example.quitnow.data.models.health

import android.content.Context
import com.example.quitnow.R
import com.example.quitnow.util.DateConverters
import com.example.quitnow.util.DateConverters.getEndTimestamp
import com.example.quitnow.util.DateConverters.toDateTime
import com.example.quitnow.util.Epoch.calcPercentage
import com.example.quitnow.util.empty


class HealthAchievementItem {

    internal var description: String = ""
    internal var finishDate: String = ""
    internal var progress: Int = 0

    fun setCardData(context: Context, index: Int, startDate: Long) {
        setDescription(context, index)
        setFinishDate(context, index, startDate)
        setProgress(index, startDate)
    }

    private fun setDescription(context: Context, index: Int) {
        description = when (index) {
            0 -> context.resources.getString(R.string.health_descr_one)
            1 -> context.resources.getString(R.string.health_descr_two)
            2 -> context.resources.getString(R.string.health_descr_three)
            3 -> context.resources.getString(R.string.health_descr_four)
            4 -> context.resources.getString(R.string.health_descr_five)
            5 -> context.resources.getString(R.string.health_descr_six)
            else -> String.empty
        }
    }

    private fun setFinishDate(context: Context, index: Int, startDate: Long) {
        finishDate = toDateTime(context, getEndDate(index, startDate))
    }

    private fun getEndDate(index: Int, startDate: Long): Long {
        return when (index) {
            0 -> getEndTimestamp(startDate, 9, DateConverters.Duration.HOURS)
            1 -> getEndTimestamp(startDate, 12, DateConverters.Duration.HOURS)
            2 -> getEndTimestamp(startDate, 24, DateConverters.Duration.HOURS)
            3 -> getEndTimestamp(startDate, 48, DateConverters.Duration.HOURS)
            4 -> getEndTimestamp(startDate, 7, DateConverters.Duration.DAYS)
            5 -> getEndTimestamp(startDate, 14, DateConverters.Duration.DAYS)
            else -> 0
        }
    }

    private fun setProgress(index: Int, startDate: Long) {
        val endDate = getEndDate(index, startDate)
        progress = calcPercentage(startDate, endDate).toInt()
    }
}