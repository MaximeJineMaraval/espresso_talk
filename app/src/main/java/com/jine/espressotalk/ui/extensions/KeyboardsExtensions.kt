package com.jine.espressotalk.ui.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.recyclerview.widget.RecyclerView

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HideKeyboardOnScroll(listState: LazyListState) {
    if (listState.isScrollInProgress) {
        LocalSoftwareKeyboardController.current?.hide()
    }
}

fun RecyclerView.closeKeyboardOnScroll() {
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    (recyclerView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
                        ?.hideSoftInputFromWindow(recyclerView.windowToken, 0)
                }
            }
        }
    )
}