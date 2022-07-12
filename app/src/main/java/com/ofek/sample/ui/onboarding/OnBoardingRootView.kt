package com.ofek.sample.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ofek.sample.R
import com.ofek.sample.presentation.onboarding.OnBoardingViewModel
import com.ofek.sample.ui.onboarding.theme.OnBoardingTheme

@Composable
fun OnBoardingRootView(
    onBoardingViewModel: OnBoardingViewModel = viewModel()
) {
    OnBoardingTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(Sizes.ONBAORDING_HORIZONTAL_PADDING.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onBoardingViewModel.openArticles()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = OnBoardingTheme.colors.navigationButtonBackgroundColor
                )
            ) {
                Text(
                    text = stringResource(id = R.string.open_articles),
                    style = OnBoardingTheme.typography.navigationButtonTextStyle,
                    color = OnBoardingTheme.colors.navigationButtonTextColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Sizes.BUTTON_SPACING.dp))
            Button(
               onClick = {
                    onBoardingViewModel.openStories()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = OnBoardingTheme.colors.navigationButtonBackgroundColor
                )
            ) {
                Text(
                    text = stringResource(id = R.string.open_stories),
                    style = OnBoardingTheme.typography.navigationButtonTextStyle,
                    color = OnBoardingTheme.colors.navigationButtonTextColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(Sizes.BUTTON_SPACING.dp))
            Button(
                onClick = {
                    onBoardingViewModel.openFavorites()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = OnBoardingTheme.colors.navigationButtonBackgroundColor
                )
            ) {
                Text(
                    text = stringResource(id = R.string.open_favorites),
                    style = OnBoardingTheme.typography.navigationButtonTextStyle,
                    color = OnBoardingTheme.colors.navigationButtonTextColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}