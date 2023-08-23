package ua.zp.testtaskjungleconsalting.presentation

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ua.zp.testtaskjungleconsalting.ReposListViewModel
import ua.zp.testtaskjungleconsalting.data.models.RepositoryItem


@Composable
fun DetailsScreen(viewModel: ReposListViewModel, login: String) {

    val isLoading = viewModel.isLoading.value
    val reposList = viewModel.reposListState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchRepos(login)
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(reposList) { repos ->
                RepoRow(repo = repos, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun RepoRow(repo: RepositoryItem, viewModel: ReposListViewModel) {
    Card(
        modifier = Modifier
            .padding(5.dp, 4.dp, 5.dp, 4.dp)
            .fillMaxWidth()
            .clickable(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }) {
                viewModel.addRepo(repo)
            },
        shape = RoundedCornerShape(CornerSize(5.dp)),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
//            Image(
//                painter = rememberAsyncImagePainter(user.avatar),
//                contentDescription = "avatar",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(RoundedCornerShape(CornerSize(10.dp)))
//            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp, 0.dp, 5.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
//                Text(
//                    text = user.id.toString(),
//                    maxLines = 1,
//                    modifier = Modifier.padding(5.dp),
//                    overflow = TextOverflow.Ellipsis,
//                    style = MaterialTheme.typography.titleMedium,
//                    fontFamily = FontFamily.Cursive
//                )
                Text(
                    text = repo.name,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Default
                )

                Text(
                    text = repo.html_url,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Default
                )
            }
        }
    }
}
