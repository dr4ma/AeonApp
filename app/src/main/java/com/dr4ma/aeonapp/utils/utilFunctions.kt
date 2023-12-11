package com.dr4ma.aeonapp.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.dr4ma.aeonapp.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun showSnackBar(context: Context, view: View, title: String, color: Int) {
    val snack = Snackbar.make(view, title, Snackbar.LENGTH_SHORT)
        .setTextColor(Color.WHITE)
        .setBackgroundTint(context.getColor(color))
        .setActionTextColor(context.getColor(R.color.white))
    snack.setAction(context.getString(R.string.cancel)) {
        snack.dismiss()
    }
    snack.show()
}

fun hideKeyBoard(context : FragmentActivity?) {
    val imm: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(context.window.decorView.windowToken, 0)
}

fun Long?.timeStampToDate() : String{
    val date = Date(this?.times(1000) ?: 0)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
    return format.format(date)
}