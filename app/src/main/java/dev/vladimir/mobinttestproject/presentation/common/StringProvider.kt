package dev.vladimir.mobinttestproject.presentation.common

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringProvider @Inject constructor(private val context: Context) {

    fun getString(@StringRes res: Int) = context.getString(res)
}
