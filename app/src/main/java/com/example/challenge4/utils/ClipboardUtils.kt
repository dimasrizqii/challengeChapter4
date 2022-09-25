package com.example.challenge4.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipboardUtils {
    fun copyToClipboard(context: Context, label: String = "challenge4", selectedText: String) {
        val clipboard: ClipboardManager? =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
        val clip = ClipData.newPlainText(label, selectedText)
        if (clipboard == null || clip == null) return
        clipboard.setPrimaryClip(clip)
    }
}