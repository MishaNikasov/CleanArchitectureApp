package com.nikasov.cleanarchitectureapp.common.extensions

import android.text.Html
import android.widget.TextView

fun TextView.htmlText(text: String) {
    this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
}