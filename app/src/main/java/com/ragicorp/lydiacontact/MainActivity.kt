package com.ragicorp.lydiacontact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ragicorp.lydiacontact.contactDetailScreen.ContactDetail.contactDetailNavigationEntry
import com.ragicorp.lydiacontact.contactDetailScreen.ContactDetail.navigateToContactDetail
import com.ragicorp.lydiacontact.contactListScreen.ContactList
import com.ragicorp.lydiacontact.ui.theme.LydiaContactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydiaContactTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ContactList.Route,
                    enterTransition = { slideInHorizontally { it } },
                    exitTransition = { fadeOut(animationSpec = tween(700)) },
                    popEnterTransition = { fadeIn(animationSpec = tween(700)) },
                    popExitTransition = { slideOutHorizontally { it } },
                ) {
                    composable(ContactList.Route) {
                        ContactList.Screen(
                            navigateToContactDetail = { contactId ->
                                navController.navigateToContactDetail(
                                    contactId
                                )
                            }
                        )
                    }

                    contactDetailNavigationEntry(
                        navigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
