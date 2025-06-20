package com.shiv.instacompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Preview
@Composable
fun CircularImagePreview() {
    CircularImage(modifier = Modifier,"", "")

}

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String,
    size: Dp = 64.dp
) {
    Image(
        modifier = modifier.size(size).clip(CircleShape).border(2.dp, Color.LightGray, CircleShape),
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )

}