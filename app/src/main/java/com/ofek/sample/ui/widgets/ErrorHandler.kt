package com.ofek.sample.ui.widgets

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.ofek.sample.presentation.errors.PresentationError


@Composable
fun ErrorHandler(
    errorState: State<PresentationError?>
) {
    val context = LocalContext.current
    val error by remember {
        errorState
    }
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it.messageRes, Toast.LENGTH_LONG).show()
        }
    }
}