package com.example.util

import androidx.ui.foundation.ScrollerPosition

/**
 * Extension on [ScrollerPosition] that checks to see if list view is at the end.
 */
val ScrollerPosition.isAtEndOfList: Boolean get() = this.value >= this.maxPosition

/**
 * Extension on [ScrollerPosition] that checks to see if list view is at the beginning.
 */
val ScrollerPosition.isAtTopOfList: Boolean get() = this.value == 0f