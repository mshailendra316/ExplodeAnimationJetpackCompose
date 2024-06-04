package com.explode.animation.ui.theme.screen

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.explode.animation.R
import com.explode.animation.ui.theme.Colors
import com.explode.animation.ui.theme.componets.MediumText
import com.explode.animation.ui.theme.componets.RegularText
import dev.omkartenkale.explodable.Explodable
import dev.omkartenkale.explodable.rememberExplosionController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun PassCodeNav() {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val scope = rememberCoroutineScope()
    val textList by remember {
        mutableStateOf(
            listOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "CLEAR"
            )
        )
    }

    var showSuccessDialog by remember {
        mutableStateOf(false)
    }

    //explosion controller
    val explosionController = rememberExplosionController()

    var passCode by rememberSaveable {
        mutableStateOf("")
    }


    val offset1 = remember {
        Animatable(0f)
    }


    val keyframes = keyframes {
        durationMillis = 100
        44f at 120
        -44f at 240
        22f at 360
        -22f at 480
        11f at 600
        -11f at 720
        0f at 800
    }



    LaunchedEffect(key1 = passCode) {
        val createdPassCode = "1111"
        if (passCode.length == 4) {
            if (passCode == createdPassCode) {
                showSuccessDialog = true
            } else {

                scope.launch {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val vibrationEffect2 =
                            VibrationEffect.createOneShot(800, VibrationEffect.DEFAULT_AMPLITUDE)
                        // it is safe to cancel other
                        vibrator.cancel()
                        vibrator.vibrate(vibrationEffect2)
                    }

                    offset1.animateTo(0f, animationSpec = keyframes).apply {
                        offset1.snapTo(0f)
                        explosionController.explode()
                    }
                }
            }
        }

    }



    Column(
        Modifier
            .background(Colors.clr_2c2324_100)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        MediumText(
            text = "Hi, John Doe",
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(5.dp))
        RegularText(
            text = "Enter your passcode",
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            repeat(4) {

                Explodable(
                    controller = explosionController,
                    onExplode = {

                        scope.launch {
                            delay(500)
                            explosionController.reset()
                            passCode = ""
                        }

                    }
                ) {
                    Box(
                        modifier = Modifier
                            .offset { IntOffset(x = offset1.value.roundToInt(), 0) }
                            .size(15.dp)
                            .then(
                                if (passCode.length > it) {
                                    Modifier
                                        .background(
                                            Colors.clr_1BB650_100,
                                            shape = CircleShape
                                        )
                                } else {
                                    Modifier
                                        .border(
                                            0.6.dp,
                                            shape = CircleShape,
                                            color = Colors.clr_1BB650_100
                                        )
                                }
                            )
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        MediumText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Forget Pin ?",
            color = Colors.clr_03AF44_100,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(25.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ) {
                items(textList) { text ->
                    if (text.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .then(
                                    if (text == "CLEAR") {
                                        Modifier.clickable {
                                            passCode = passCode.dropLast(1)
                                        }
                                    } else {
                                        Modifier
                                            .clickable {
                                                if (passCode.length != 4) {
                                                    passCode += text
                                                }
                                            }
//                                            .border(
//                                                0.6.dp,
//                                                color = Colors.clr_DDDEDF_100,
//                                                shape = RoundedCornerShape(15.dp)
//                                            )
                                            .background(
                                                Colors.clr_3e3435_100,
                                                shape = RoundedCornerShape(15.dp)
                                            )
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (text == "CLEAR") {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_clear),
                                    contentDescription = "clear icon",
                                    Modifier.size(30.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                            } else {
                                MediumText(
                                    text = text,
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.padding(30.dp))
        }

    }





}
