package ua.zp.testtaskjungleconsalting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ua.zp.testtaskjungleconsalting.presentation.Navigation
import ua.zp.testtaskjungleconsalting.ui.theme.TestTaskJungleConsaltingTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: UsersListViewModel by viewModels()
    private val viewModel2: ReposListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskJungleConsaltingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        userListViewModel = viewModel,
                        reposListViewModel = viewModel2,
                        context = this@MainActivity
                    )
                }
            }
        }
    }
}

