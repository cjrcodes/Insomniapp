package com.cjrcodes.wearosmodule.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.TimeText
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.cjrcodes.wearosmodule.viewmodel.CountdownViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

class AlarmScreenActivity : ComponentActivity()

@Destination<RootGraph>
@Composable
fun AlarmScreen(time: Int) {
    TimeText()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            val viewModel = viewModel<CountdownViewModel>()
            LaunchedEffect(key1 = time) {
                viewModel.setStartTime(time * 60 * 1000L) // Convert minutes to milliseconds
            }

            val timerState by viewModel.timerState.collectAsStateWithLifecycle()
            val countDownText by viewModel.countDownText.collectAsStateWithLifecycle()
            StopWatch(
                state = timerState,
                text = countDownText,
                onToggleRunning = viewModel::toggleIsRunning,
                onReset = viewModel::resetTimer,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun AlarmScreenActivityPreview() {
    AlarmScreen(10)
}
