package com.ragicorp.lydiacontact.contactDetailScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
                contactId = contactId
            )
        }
    }

    fun NavHostController.navigateToContactDetail(
        contactId: String
    ) {
        navigate("$RouteBase?$ContactArgument=$contactId")
    }

    @Composable
    private fun Screen(
        navigateBack: () -> Unit,
        contactId: String
    ) {

    }
}