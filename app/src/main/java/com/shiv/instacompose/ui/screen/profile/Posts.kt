package com.shiv.instacompose.ui.screen.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shiv.instacompose.domain.model.UsersPost

@Composable
fun FeedPosts(userPost: LazyPagingItems<UsersPost>) {
    val gridState = rememberLazyGridState()
  LazyVerticalGrid(
      state = gridState,
      columns = GridCells.Fixed(3)) {
      items(userPost.itemCount){index->
          userPost[index]?.let { PostImageItem(it) }
      }

      if(userPost.loadState.append is LoadState.Loading){
          item(span = { GridItemSpan(maxLineSpan) }) {
              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(16.dp),
                  contentAlignment = Alignment.Center
              ) {
                  CircularProgressIndicator(
                      color = Color.White,
                      strokeWidth = 2.dp,
                      modifier = Modifier.size(24.dp)
                  )
              }
          }
      }

  }
}
@Composable
fun PostImageItem(post: UsersPost) {
    val context = LocalContext.current
    val model = remember(post.postThumb) { ImageRequest.Builder(context)
        .data(post.postThumb)
        .crossfade(true)
        .build()
    }
    AsyncImage(
        model = model,
        contentDescription = null,
        modifier = Modifier.border(1.dp, color = Color.Black)
            .aspectRatio(2/3f),
        contentScale = ContentScale.Crop
    )
}