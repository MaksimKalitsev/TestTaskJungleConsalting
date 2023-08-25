package ua.zp.testtaskjungleconsalting.presentation.detailsscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ua.zp.testtaskjungleconsalting.data.models.RepoItem


@Composable
fun DetailsScreen(repos:LazyPagingItems<RepoItem>) {

    val context = LocalContext.current
    LaunchedEffect(key1 = repos.loadState) {
        if(repos.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (repos.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if(repos.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(repos) { repo ->
                    if(repo != null) {
                        RepoRow(
                            repo = repo
                        )
                    }
                }
                item {
                    if(repos.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}

@Composable
fun RepoRow(repo: RepoItem, ) {
    Card(
        modifier = Modifier
            .padding(5.dp, 4.dp, 5.dp, 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(5.dp)),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp, 0.dp, 5.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = repo.id.toString(),
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Cursive
                )
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
