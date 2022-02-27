package com.example.android_ca.mock

import com.example.android_ca.core.api.model.ResponseInfo
import org.jetbrains.annotations.TestOnly

@TestOnly
fun ResponseInfo.Companion.mock() = ResponseInfo(
    count = 2,
    pages = 5,
    next = "Example next",
    prev = "Example prev"
)
