package ua.zp.testtaskjungleconsalting.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import ua.zp.testtaskjungleconsalting.data.models.User

@Composable
fun StartScreen(navController: NavController, users:LazyPagingItems<User>) {

    val context = LocalContext.current
    LaunchedEffect(key1 = users.loadState) {
        if(users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (users.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if(users.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(users) { user ->
                    if(user != null) {
                        UserRow(
                            user = user,
                            navController = navController
                        )
                    }
                }
                item {
                    if(users.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

    @Composable
    fun UserRow(user: User, navController: NavController) {
        Card(
            modifier = Modifier
                .padding(5.dp, 4.dp, 5.dp, 4.dp)
                .fillMaxWidth()
                .clickable(indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }) {
                    navController.navigate(Screen.DetailsScreen.route.replace("{login}", user.login))
                },
            shape = RoundedCornerShape(CornerSize(5.dp)),
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))
                )
                Column(
                    modifier = Modifier
                        .padding(10.dp, 5.dp, 0.dp, 5.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = user.id.toString(),
                        maxLines = 1,
                        modifier = Modifier.padding(5.dp),
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = FontFamily.Default
                    )
                    Text(
                        text = user.login,
                        maxLines = 1,
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = FontFamily.Default
                    )

                    Text(
                        text = user.repos_url,
                        maxLines = 1,
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontFamily = FontFamily.Default
                    )
                }
            }
        }
    }