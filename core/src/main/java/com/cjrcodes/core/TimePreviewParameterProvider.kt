package com.cjrcodes.core

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import java.time.LocalTime

class TimePreviewParameterProvider : PreviewParameterProvider<LocalTime> {
    override val values: Sequence<LocalTime>
        @RequiresApi(Build.VERSION_CODES.O)
        get() = sequenceOf(LocalTime.of(0, 5)
        )
}