package ua.zp.testtaskjungleconsalting.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ua.zp.testtaskjungleconsalting.UsersListViewModel
import ua.zp.testtaskjungleconsalting.data.models.User

@Composable
fun StartScreen(navController: NavController, viewModel: UsersListViewModel) {

    val isLoading = viewModel.isLoading.value
    val userList = viewModel.userListState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(userList) { user ->
                UserRow(user = user, viewModel = viewModel, navController = navController)
            }
        }
    }
}

    @Composable
    fun UserRow(user: User, viewModel: UsersListViewModel, navController: NavController) {
        Card(
            modifier = Modifier
                .padding(5.dp, 4.dp, 5.dp, 4.dp)
                .fillMaxWidth()
                .clickable(indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }) {
                    viewModel.addUser(user)
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