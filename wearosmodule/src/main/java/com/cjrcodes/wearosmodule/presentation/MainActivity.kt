/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.cjrcodes.wearosmodule.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.PickerState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.rememberPickerState
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import com.cjrcodes.wearosmodule.presentation.theme.InsomniappTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.AlarmScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}

@Destination<RootGraph>(start = true)
@Composable
fun WearApp(navigator: DestinationsNavigator) {
    InsomniappTheme {
        val items = (1..60).map { it.toString() }

        val pickerState = rememberPickerState(items.size, 4, true)
        TimeText()
        WearAppContent(
            pickerState,
            items,
             onButtonClick = { selectedTime ->
                navigator.navigate(
                    AlarmScreenDestination(
                        selectedTime
                    )
                )
            }
        )
    }
}


@Composable
fun WearAppContent(
    pickerState: PickerState,
    items: List<String>,
    onButtonClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val scrollState = rememberScrollState()
            val padding = PaddingValues(20.dp)
            val selectedItem = remember { mutableStateOf(pickerState.selectedOption) }
            Picker(
                state = pickerState,
                contentDescription = "Time Picker",
                modifier = Modifier.fillMaxHeight(0.50f),
                readOnly = false,
                readOnlyLabel = null,
                separation = 20.dp,
                gradientRatio = 0.5f,
                gradientColor = Color.Black,
                userScrollEnabled = true,
                option = {
                    Text(items[it])
                },
            )
            Button(
                onClick = {onButtonClick(pickerState.selectedOption)},
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
                    .offset(y = 20.dp)
            ) {
                Text("Set Alarm")
            }
        }
    }
}

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun MainActivityPreview() {
    WearApp(EmptyDestinationsNavigator)
}
