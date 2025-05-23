package com.example.viajesmart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.viajesmart.viewmodels.RideViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBar(
    viewModel: RideViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterOption.values().forEach { filter ->
            FilterChip(
                selected = viewModel.currentFilter?.key == filter.key,
                onClick = {
                    viewModel.applyFilter(
                        if (viewModel.currentFilter?.key == filter.key) null
                        else filter
                    )
                },
                label = { Text(stringResource(filter.displayNameRes)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    }
}