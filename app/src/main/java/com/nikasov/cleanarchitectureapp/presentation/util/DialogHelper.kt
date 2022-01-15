package com.nikasov.cleanarchitectureapp.presentation.util

import android.content.Context
import com.nikasov.cleanarchitectureapp.common.extensions.showDialog
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DialogHelper constructor(
   private val context: Context
) {

    fun deleteFromFavorite(id: String = "") {
        context.showDialog(
            title = "Delete from favorite?",
            positiveBtn = Pair(
                "Yes", {
                    it.dismiss()
                }
            ),
            negativeBtn = Pair(
                "No", {
                    it.dismiss()
                }
            )
        )
    }

}