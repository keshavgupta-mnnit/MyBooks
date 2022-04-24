package com.keshav.mybooks.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keshav.mybooks.ui.theme.typography

@ExperimentalComposeUiApi
@Composable
fun TextInputView(label: String, value: String, onValueChanged: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        label = { LabelView(label) } ,
        colors = textFieldColors(),
        textStyle = typography.body1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions (
            onDone = { keyboardController?.hide() }
        )
    )
}

@Composable
fun LabelView(title: String) {
    Text(
        text = title,
        style = typography.caption,
        textAlign = TextAlign.Start,
        color = Color.Black
    )
}

@Composable
fun textFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = colors.primaryVariant,
    focusedLabelColor = colors.primary,
    focusedIndicatorColor = colors.primary,
    unfocusedIndicatorColor = Color.LightGray,
    cursorColor = colors.primary,
    placeholderColor = colors.onSurface,
    disabledPlaceholderColor = colors.onSurface
)

