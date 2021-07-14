package com.untels.oyevecino

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable

object CommonUtils {
    fun ShowDialogoCarga(context: Context):Dialog{
        val dialogoProceso = Dialog(context)
        dialogoProceso.let{
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.dialogo_progreso)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)

            return it
        }
    }
}