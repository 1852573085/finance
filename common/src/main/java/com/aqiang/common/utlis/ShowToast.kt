package com.aqiang.common.utlis

import android.content.Context
import android.widget.Toast

object ShowToast {
    fun toast(context: Context, msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }
}
