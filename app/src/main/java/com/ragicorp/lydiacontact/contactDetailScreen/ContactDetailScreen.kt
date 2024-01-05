package com.ragicorp.lydiacontact.contactDetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ragicorp.lydiacontact.R
import com.ragicorp.lydiacontact.contactDetailScreen.views.AddressCard
import com.ragicorp.lydiacontact.contactDetailScreen.views.EmailCard
import com.ragicorp.lydiacontact.contactDetailScreen.views.PhoneNumberCard
import com.ragicorp.lydiacontact.ui.theme.Spacing
import com.ragicorp.lydiacontact.ui.views.ContactImage
import com.ragicorp.lydiacontact.ui.views.ImageSize
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
            val contactValue = contact.value
            if (contactValue != null) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(Spacing.screen),
                        verticalArrangement = Arrangement.spacedBy(Spacing.single * 4),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ContactImage(size = ImageSize.LARGE, url = contactValue.picture.large)
                        PhoneNumberCard(phoneNumber = contactValue.phone)
                        EmailCard(email = contactValue.email)
                        AddressCard(address = contactValue.generateReadableAddress())
                    }
                }
            }
        }
    }
}