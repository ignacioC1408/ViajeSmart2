package com.example.viajesmart.ui.components

import androidx.annotation.StringRes
import com.example.viajesmart.R

enum class FilterOption(
    @StringRes val displayNameRes: Int,
    val key: String
) {
    ALL(R.string.filter_all, "all"),
    PRICE(R.string.filter_price, "price"),
    CHEAPEST(R.string.filter_cheapest, "cheapest"),
    FASTEST(R.string.filter_fastest, "fastest"),
    PREMIUM(R.string.filter_premium, "premium"),
    TIME(R.string.filter_time, "time");

    companion object {
        fun values() = entries.toTypedArray()
    }
}