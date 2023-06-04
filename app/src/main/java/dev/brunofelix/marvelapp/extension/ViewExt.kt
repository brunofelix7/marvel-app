package dev.brunofelix.marvelapp.extension

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this.context, message, duration).show()
}

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}