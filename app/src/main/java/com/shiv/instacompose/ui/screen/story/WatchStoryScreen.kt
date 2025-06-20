package com.shiv.instacompose.ui.screen.story

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.ui.screen.profile.UiState
import com.shiv.instacompose.ui.screen.profile.UserProfileViewModel
import kotlinx.coroutines.delay


@Composable
fun StoryPlayer(index: String, navigateTo: (String) -> Unit, navigateUp: () -> Unit) {
    val viewModel = hiltViewModel<UserProfileViewModel>()
    val storyState = viewModel.usersStoryState.collectAsState()
    val currentIndex = remember { mutableStateOf(index.toInt()) }
    val stories = remember { mutableStateOf<List<UsersStory>>(emptyList()) }

    LaunchedEffect(storyState.value) {
        if (storyState.value is UiState.Success)
            stories.value = (storyState.value as UiState.Success).data.toMutableList()
    }
    val currentStory = remember {
        derivedStateOf {
            stories.value.getOrNull(currentIndex.value)
        }
    }

    Box(
        modifier = Modifier
            .background(Color.Black)
            .padding(top = 50.dp, bottom = 50.dp, start = 8.dp, end = 8.dp)
    ) {

        AsyncImage(
            model = currentStory.value?.postUrl,
            contentDescription = "Story Player",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(4)),
        )
        // Progress bars
        if (stories.value.isNotEmpty())
            StoryProgressIndicatorRow(stories.value.size, currentIndex.value, false, 3000, {
                if (currentIndex.value < stories.value.size - 1) {
                    currentIndex.value++
                } else {
                    navigateUp()
                }
            })
    }
    // Auto-advance logic
    LaunchedEffect(currentIndex.value) {
        delay(3000)
        if (currentIndex.value < stories.value.lastIndex) {
            currentIndex.value++
        } else {
            navigateUp()
        }
    }
}

@Composable
fun StoryProgressIndicatorRow(
    totalStories: Int,
    currentIndex: Int,
    isPaused: Boolean,
    durationMillis: Long,
    onNext: () -> Unit
) {
    // Create individual animatables for all segments
    val segmentProgressList = remember(totalStories) {
        List(totalStories) { Animatable(if (it == currentIndex) 0f else 0f) }
    }

    // Reset and animate current segment
    LaunchedEffect(currentIndex, isPaused) {
        if (!isPaused && currentIndex < totalStories) {
            // Reset progress for current
            segmentProgressList[currentIndex].snapTo(0f)
            segmentProgressList[currentIndex].animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = durationMillis.toInt(),
                    easing = LinearEasing
                )
            )
            onNext()
        } else {
            // If paused, stop animation
            segmentProgressList[currentIndex].stop()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(totalStories) { index ->
            val progress = when {
                index < currentIndex -> 1f
                index == currentIndex -> segmentProgressList[index].value
                else -> 0f
            }

            LinearProgressIndicator(
                progress = progress.coerceIn(0f, 1f),
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.3f)
            )
        }
    }
}

