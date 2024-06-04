package com.explode.animation.ui.theme.componets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.explode.animation.ui.theme.Fonts


@Composable
fun RegularText(
    modifier : Modifier = Modifier,
    text : String = "",
    fontSize : TextUnit = 10.sp,
    maxLines : Int = Int.MAX_VALUE,
    lineHeight : TextUnit = fontSize,
    color : Color = MaterialTheme.colorScheme.tertiary,
    textAlign : TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = Fonts.fontRegular,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        lineHeight = lineHeight,
        color = color,
        letterSpacing = 0.sp,
        textAlign = textAlign
    )
}

@Composable
fun MediumText(
    modifier : Modifier = Modifier,
    text : String = "",
    fontSize : TextUnit = 10.sp,
    maxLines : Int = Int.MAX_VALUE,
    lineHeight : TextUnit = fontSize,
    color : Color = MaterialTheme.colorScheme.tertiary,
    textAlign: TextAlign= TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier,
        fontFamily = Fonts.fontMedium,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        lineHeight = lineHeight,
        color = color,
        letterSpacing = 0.sp,
        textAlign = textAlign
    )
}