package com.nikasov.cleanarchitectureapp.common.extensions

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDialog(
    title: String,
    msg: String? = null,
    positiveBtn: Pair<String, (DialogInterface) -> Unit>? = null,
    negativeBtn: Pair<String, (DialogInterface) -> Unit>? = null,
    isCancelable: Boolean = true
) {
    val builder = MaterialAlertDialogBuilder(this)
        .setTitle(title)

    positiveBtn?.let {
        builder.setPositiveButton(it.first) { dialog, _ ->
            it.second(dialog)
        }
    }
    negativeBtn?.let {
        builder.setNegativeButton(it.first) { dialog, _ ->
            it.second(dialog)
        }
    }

    msg.let {
        builder.setMessage(it)
    }

    builder.setCancelable(isCancelable)

    builder.show()
}