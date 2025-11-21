package com.example.lab_week_09_sandy

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_week_09_sandy.providers.MoshiProvider
import com.example.lab_week_09_sandy.ui.theme.LAB_WEEK_09_SandyTheme
import com.example.lab_week_09_sandy.ui.theme.OnBackgroundItemText
import com.example.lab_week_09_sandy.ui.theme.OnBackgroundTitleText
import com.example.lab_week_09_sandy.ui.theme.PrimaryTextButton
import com.example.lab_week_09_sandy.ui.theme.StudentCard
import com.squareup.moshi.Json
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09_SandyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(
                        navController = navController
                    )
                }
            }
        }
    }
}

data class Student(
    @Json(name = "name")
    var name: String,
    @Json(name = "studentNIM")
    var studentNIM: String,
)

@Composable
fun App(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            Home {
                navController.navigate(
                    "resultContent/?listData=$it"
                )
            }
        }
        composable(
            "resultContent/?listData={listData}",
            arguments = listOf(
                navArgument("listData")
                {
                    type = NavType.StringType
                }
            )
        ) {
            ResultContent(
                it.arguments?.getString("listData").orEmpty()
            )
        }
    }
}

@Composable
fun Home(
    navigateFromHomeToResult: (String) -> Unit
) {
    val listData = remember {
        mutableStateListOf(
            Student("Tanu", "00000170819"),
            Student("Tina", "00001717778"),
            Student("Tono", "00000198989")
        )
    }

    var inputField = remember {
        mutableStateOf(Student("", ""))
    }

    val json = MoshiProvider.studentListAdapter.toJson(listData)
    val encoded = Uri.encode(json)

    HomeContent(
        listData,
        inputField.value,
        { input ->
            inputField.value = inputField.value.copy(input)
        },
        {
            if (inputField.value.name.trim().isNotBlank()) {
                val trimmedName = inputField.value.name.trim()
                val randomNIM = Random.nextInt(0, 9_9999_999) // adjust max if you want
                val formattedNIM = String.format("%011d", randomNIM)
                listData.add(
                    Student(
                        name = trimmedName,
                        studentNIM = formattedNIM
                    )
                )
                inputField.value = Student("", "")
            }
        },
        {
            navigateFromHomeToResult(encoded)
        }
    )
}

@Composable
fun HomeContent(
    listData: SnapshotStateList<Student>,
    inputField: Student,
    onInputValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    navigateFromHomeToResult: () -> Unit
) {
    val isAddEnabled =
        inputField.name.trim().isNotBlank() && inputField.name.matches(Regex("^[A-Za-z'\\- ]+$"))
    val isFinishEnabled = listData.isNotEmpty()

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(
//                    text = stringResource(R.string.add_name_placeholder)
//                )
                OnBackgroundTitleText(
                    text = stringResource(R.string.add_name_placeholder)
                )
                TextField(
                    value = inputField.name,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        onInputValueChange(it)
                    }
                )
                Row(
                    //
                ) {
                    PrimaryTextButton(
                        text = stringResource(R.string.add_name_button),
                        isEnabled = isAddEnabled
                    ) {
                        onButtonClick()
                    }
                    PrimaryTextButton(
                        text = stringResource(R.string.navigation_button),
                        isEnabled = isFinishEnabled
                    ) {
                        navigateFromHomeToResult()
                    }
                }
//                Button(onClick = {
//                    onButtonClick()
//                }) {
//                    Text(
//                        text = stringResource(
//                            id = R.string.add_name_button
//                        )
//                    )
//                }
            }
        }
        items(listData) { item ->
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBackgroundItemText(
                    text = item.name
                )
                //                Text(
//                    text = item.name
//                )
            }
        }
    }
}

@Composable
fun ResultContent(
    listDataFromJson: String,
) {
    val decoded = remember(listDataFromJson) {
        val json = Uri.decode(listDataFromJson)
        MoshiProvider.studentListAdapter.fromJson(json) ?: emptyList()
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(decoded) { student ->
            StudentCard(student)
        }
    }
}