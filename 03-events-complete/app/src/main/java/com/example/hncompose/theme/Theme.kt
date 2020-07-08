/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hncompose.theme

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val LightThemeColors = lightColorPalette(
    primary = Orange700,
    primaryVariant = Orange900,
    onPrimary = Color.White,
    secondary = Orange700,
    secondaryVariant = Orange900,
    onSecondary = Color.White,
    error = Orange800
)

private val DarkThemeColors = darkColorPalette(
    primary = Orange300,
    primaryVariant = Orange700,
    onPrimary = Color.Black,
    secondary = Orange300,
    onSecondary = Color.White,
    error = Orange200
)

@Composable
val ColorPalette.snackbarAction: Color
    get() = if (isLight) Orange300 else Orange700

@Composable
fun JetnewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = ThemeTypography,
        shapes = Shapes,
        content = content
    )
}
