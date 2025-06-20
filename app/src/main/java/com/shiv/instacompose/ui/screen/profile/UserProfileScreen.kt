package com.shiv.instacompose.ui.screen.profile

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.shiv.instacompose.R
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.ui.component.CircularImage
import com.shiv.instacompose.ui.theme.Purple40


@Preview
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen() {
    val viewModel = hiltViewModel<UserProfileViewModel>()
    val userProfile = viewModel.userProfileState.collectAsStateWithLifecycle()
    val userStoryUiState = viewModel.usersStoryState.collectAsStateWithLifecycle()
    val userPost = viewModel.posts.collectAsLazyPagingItems()
    val selectedTab = viewModel.selectedTab
    val pagerState = rememberPagerState(initialPage = 0) { ProfileTab.entries.size }

    LaunchedEffect(Unit) {
        viewModel.getUsersProfile()
        viewModel.refreshUserProfileDetails()
        viewModel.getUsersStory()
        viewModel.refreshUserStory()
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTab.value = ProfileTab.entries[pagerState.currentPage]
    }
    LaunchedEffect(selectedTab.value) {
        pagerState.animateScrollToPage(selectedTab.value.ordinal)
    }
    Scaffold(
        topBar = {
            HandleProfileHeaderUiState(userProfile.value)
        }, content = { innerpadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(innerpadding)
            ) {
                val screenHeight = maxHeight
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = scrollState)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        HandleProfileDetailsUiState(userProfile.value)
                        HandleStoryUiState(userStoryUiState.value)
                    }
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .height(screenHeight)
                    ) {
                        StickyTab(selectedTab)
                        Postspager(pagerState, scrollState, userPost)
                    }
                }
            }
        })
}

@Composable
private fun Postspager(
    pagerState: PagerState,
    scrollState: ScrollState,
    userPost: LazyPagingItems<UsersPost>
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxHeight()
            .nestedScroll(remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        return if (available.y > 0) Offset.Zero else Offset(
                            x = 0f,
                            y = -scrollState.dispatchRawDelta(-available.y)
                        )
                    }
                }
            })
    ) { page ->
        when (page) {
            0 -> {
                FeedPosts(userPost)
            }

            1 -> {
                FeedPosts(userPost)
            }

            2 -> {
                FeedPosts(userPost)
            }
        }
    }
}

@Composable
private fun HandleStoryUiState(value: UiState<List<UsersStory>>) {
    when (value) {
        is UiState.Success -> {
            val stories = (value).data
            UsersStoryHighlightSection(stories)
        }

        else -> {
            /**
             * not handled loading and error usecase here for simplicity
             */
            /**
             * not handled loading and error usecase here for simplicity
             */
        }
    }
}

@Composable
private fun HandleProfileHeaderUiState(userProfile: UiState<UserProfile>) {
    when (userProfile) {
        is UiState.Success -> {
            val user = (userProfile).data
            ProfileHeader(user)
        }

        else -> {
            /**
             * not handled loading and error usecase here for simplicity
             */
            /**
             * not handled loading and error usecase here for simplicity
             */
        }
    }
}

@Composable
private fun HandleProfileDetailsUiState(userProfile: UiState<UserProfile>) {
    when (userProfile) {
        is UiState.Success -> {
            val user = (userProfile).data
            ProfileDetailSection(user)
        }

        else -> {
            /**
             * not handled loading and error usecase here for simplicity
             */
            /**
             * not handled loading and error usecase here for simplicity
             */
        }
    }
}

@Composable
fun ProfileHeader(user: UserProfile) {
    ConstraintLayout(modifier = Modifier.padding(top = 16.dp).fillMaxWidth()) {
        val (imgBack, txtUsername) = createRefs()
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = "back Icon",
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(imgBack) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clickable {}
        )
        Text(
            text = user.userName, modifier = Modifier
                .padding(8.dp)
                .constrainAs(txtUsername) {
                    start.linkTo(imgBack.end)
                    top.linkTo(parent.top)
                }, fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ProfileDetailSection(
    user: UserProfile
) {
    ConstraintLayout {
        val (txtUsername, imgProfile, txtName, statsSection, txtBio, followBarRow) = createRefs()
        CircularImage(
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(imgProfile) {
                    top.linkTo(txtUsername.bottom)
                    start.linkTo(parent.start)
                },
            user.profileThumbUrl, contentDescription = "user profile image", 80.dp
        )
        Text(
            text = user.name, modifier = Modifier
                .padding(8.dp)
                .constrainAs(txtName) {
                    top.linkTo(imgProfile.top)
                    start.linkTo(imgProfile.end)
                }, fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )

        val statsModifier = Modifier.Companion.constrainAs(statsSection) {
            start.linkTo(txtName.start)
            top.linkTo(txtName.bottom)
            end.linkTo(parent.end)
        }
        ProfileStatsSection(statsModifier, user)
        Text(
            text = user.bio, modifier = Modifier
                .padding(8.dp)
                .constrainAs(txtBio) {
                    top.linkTo(imgProfile.bottom)
                    start.linkTo(imgProfile.start)
                }, fontSize = 14.sp,
            color = Purple40,
            fontWeight = FontWeight.Medium
        )
        val followBarModifier = Modifier.Companion.constrainAs(followBarRow) {
            start.linkTo(txtBio.start)
            top.linkTo(txtBio.bottom)
        }
        FollowingMessageBar(followBarModifier)
    }
}

@Composable
fun FollowingMessageBar(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .padding(end = 4.dp)
                .weight(1f)
                .height(34.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.DarkGray
            )
        ) {
            Text(
                text = stringResource(R.string.following).replaceFirstChar { it.uppercase() },
                color = Color.White,
                fontSize = 14.sp
            )
            Icon(
                modifier = Modifier.padding(top = 2.dp),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "following",
                tint = Color.White
            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)
                .height(34.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.DarkGray
            )
        ) {
            Text(
                text = stringResource(R.string.message).replaceFirstChar { it.uppercase() },
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun UsersStoryHighlightSection(highlights: List<UsersStory>) {
    LazyRow(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .padding(top = 16.dp)
            .wrapContentSize()
    ) {
        items(highlights.size) { index ->
            CircularImage(
                modifier = Modifier.padding(8.dp),
                imageUrl = highlights[index].postThumb,
                contentDescription = "Story Highlight"
            )
        }
    }
}

@Composable
fun StickyTab(selectedTab: MutableState<ProfileTab>) {
    TabRow(
        modifier = Modifier.background(color = Color.Black),
        selectedTabIndex = selectedTab.value.ordinal,
        containerColor = Color.Black,
        indicator = { tabPositions ->
            val transition = updateTransition(
                targetState = selectedTab.value.ordinal,
                label = "Tab indicator transition"
            )

            val indicatorLeft by transition.animateDp(
                transitionSpec = { spring(stiffness = Spring.StiffnessMedium) },
                label = "Indicator Left"
            ) { tabPositions[it].left }

            val indicatorWidth by transition.animateDp(
                transitionSpec = { spring(stiffness = Spring.StiffnessMedium) },
                label = "Indicator Width"
            ) { tabPositions[it].width }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart) // ✅ Align bottom
                    .offset(x = indicatorLeft)
                    .width(indicatorWidth)
                    .height(2.dp) // ✅ Slim indicator height
                    .background(Color.White)
            )
        }
    ) {
        ProfileTab.entries.forEachIndexed { index, profileTab ->
            Tab(
                selected = selectedTab.value.ordinal == index,
                onClick = {
                    selectedTab.value = profileTab
                }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = when (profileTab) {
                        ProfileTab.Posts -> Icons.Default.Menu
                        ProfileTab.Reels -> Icons.Default.PlayArrow
                        ProfileTab.Tagged -> Icons.Default.AccountBox
                    },
                    contentDescription = profileTab.name,
                    tint = if (selectedTab.value.ordinal == index) Color.White else Color.Gray
                )
            }
        }

    }
}




