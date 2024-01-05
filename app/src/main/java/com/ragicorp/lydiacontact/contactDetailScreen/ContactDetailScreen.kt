package com.ragicorp.lydiacontact.contactDetailScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ragicorp.lydiacontact.R
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.util.UUID

internal object ContactDetail {
    private const val RouteBase = "contactDetail"
    private const val ContactArgument = "contact"
    private const val Route = "$RouteBase?$ContactArgument={$ContactArgument}"

    // Wrappers for type-safety
    fun NavGraphBuilder.contactDetailNavigationEntry(
        navigateBack: () -> Unit
    ) {
        composable(
            Route,
            arguments = listOf(
                navArgument(ContactArgument) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val args = it.arguments ?: throw IllegalArgumentException()
            val contactId = args.getString(ContactArgument) ?: throw IllegalStateException()

            Screen(
                navigateBack = navigateBack,
                contactDetailViewModel = koinViewModel(parameters = {
                    parametersOf(
                        UUID.fromString(
                            contactId
                        )
                    )
                })
            )
        }
    }

    fun NavHostController.navigateToContactDetail(
        contactId: String
    ) {
        navigate("$RouteBase?$ContactArgument=$contactId")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Screen(
        navigateBack: () -> Unit,
        contactDetailViewModel: ContactDetailViewModel
    ) {
        val contact = contactDetailViewModel.contact.collectAsStateWithLifecycle(null)

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navigateBack() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.backButton)
                            )
                        }
                    },
                    title = {
                        Text(text = contact.value?.textToShow ?: "")
                    }
                )
            }
        ) {
            if (contact.value != null) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(
                        text = contact.value!!.generateReadableAddress()
                    )
                }
            }
        }
    }
}