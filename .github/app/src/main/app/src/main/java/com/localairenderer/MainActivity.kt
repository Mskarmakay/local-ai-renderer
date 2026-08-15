package com.localairenderer

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private var selectedImage by mutableStateOf<Uri?>(null)

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            selectedImage = uri
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var prompt by mutableStateOf("")
            var negativePrompt by mutableStateOf("")

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text("Local AI Renderer")

                Button(
                    onClick = {
                        imagePicker.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (selectedImage == null)
                            "Choose Full Photo"
                        else
                            "Photo Selected"
                    )
                }

                OutlinedTextField(
                    value = prompt,
                    onValueChange = { prompt = it },
                    label = { Text("Image generation prompt") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = negativePrompt,
                    onValueChange = { negativePrompt = it },
                    label = { Text("Negative prompt") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        // Local diffusion engine will be connected here.
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("GENERATE")
                }

                Text(
                    if (selectedImage == null)
                        "No photo selected"
                    else
                        "Photo ready for local rendering"
                )
            }
        }
    }
}
