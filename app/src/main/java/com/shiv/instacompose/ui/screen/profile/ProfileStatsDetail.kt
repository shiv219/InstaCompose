package com.shiv.instacompose.ui.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shiv.instacompose.R
import com.shiv.instacompose.domain.model.UserProfile

@Preview
@Composable
fun ProfileDetailSectionPreview() {
    ProfileStatsSection(Modifier,null)
}

@Composable
fun ProfileStatsSection(modifier: Modifier, user: UserProfile?) {
    user?.let {
        Row(modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Stats(it.postsCount, stringResource(R.string.posts))
            Stats(it.followerCount,  stringResource(R.string.followers))
            Stats(it.followingCount,  stringResource(R.string.following))
        }
    }
}

@Composable
fun Stats(count: String, label: String) {
    Column(modifier = Modifier.wrapContentHeight()) {
        Text(text = count, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        Text(text = label, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)

    }
}