package com.petcarehub.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petcarehub.coremodel.Species

/**
 * Toolchain proof, not a product screen.
 *
 * It exists to show that the Gradle wrapper, AGP, Kotlin Multiplatform, Compose Multiplatform
 * and a shared `core-model` dependency all build and run together on a device. Replace it with
 * the Navigation 3 host from `shared/app-ui` in Phase 1 — nothing here is worth keeping.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ToolchainProof()
                }
            }
        }
    }
}

@Composable
private fun ToolchainProof() {
    // Reading from core-model here is the point: it proves the shared module is on the
    // classpath and that the layering rule (app -> core-model) resolves.
    val species: List<Species> = Species.Known.entries + Species.Other("Bearded dragon")

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Pet Care Hub", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Toolchain check", style = MaterialTheme.typography.bodyMedium)
        species.forEach { entry ->
            val label = when (entry) {
                is Species.Known -> entry.key
                is Species.Other -> entry.label
            }
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }
}
