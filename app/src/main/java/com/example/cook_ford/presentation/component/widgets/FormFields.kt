package com.example.cook_ford.presentation.component.widgets
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.component.customeComposableViews.ErrorTextInputField

/*
@Composable
fun InputPasswordTextField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password") {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }


    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}*/


data class KeyboardOption(
    val imeAction: ImeAction,
    val keyboardType: KeyboardType,
    val label: String,
    val placeholder: String
)

data class DefaultIcons(val leadingIcon: ImageVector, val trailingIcon: TrailingIcon?=null)

data class TrailingIcon(val visibilityOff: ImageVector, val visibility: ImageVector?=null)


@Composable
fun InputTextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOption,
    defaultIcons: DefaultIcons,
    isError: Boolean = false,
    errorText: String = "",
    maxChar: Int = 0,
    /*submit: () -> Unit*/) {

    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusManager = LocalFocusManager.current

    val leadingIcon = @Composable {
        Icon(defaultIcons.leadingIcon,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            (if (isPasswordVisible) defaultIcons.trailingIcon?.visibilityOff else defaultIcons.trailingIcon?.visibility)?.let { visibility->
                Icon(
                    visibility,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }


    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= maxChar) onChange.invoke(it)},
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = keyboardOptions.imeAction, keyboardType = keyboardOptions.keyboardType),
        keyboardActions = KeyboardActions {
            if (keyboardOptions.imeAction == ImeAction.Done) {
                keyboardController?.hide()
            }else{
                focusManager.moveFocus(FocusDirection.Down)
            }
        },
        placeholder = { Text(keyboardOptions.placeholder) },
        label = { Text(keyboardOptions.label) },
        singleLine = true,
        maxLines = 1,
        visualTransformation = if (keyboardOptions.keyboardType ==KeyboardType.Password){
            if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        }else{
            VisualTransformation.None
        },
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        }
    )
}

@Composable
fun Textarea(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOption,
    isError: Boolean = false,
    errorText: String = "",
    maxChar: Int = 0,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = {if (it.length <= maxChar) onChange.invoke(it)},
        isError = isError,
        keyboardOptions = KeyboardOptions(imeAction = keyboardOptions.imeAction, keyboardType = keyboardOptions.keyboardType),
        placeholder = { Text(keyboardOptions.placeholder) },
        label = { Text(keyboardOptions.label) },
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        }
    )
}
@Composable
fun RadioButton() {
    val selectedValue = remember { mutableStateOf("") }
    val label = "Item"
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedValue.value == label,
            onClick = { selectedValue.value = label }
        )
        Text(text = label,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MultipleRadioButtons(onChange: (String) -> Unit) {

    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("Male", "Female", "Other")

  Column(verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {

      Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")

      Row(horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
              .padding(10.dp)
              .fillMaxWidth()){

          items.forEach { item ->
              Row (horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                      .selectable(
                          selected = isSelectedItem(item),
                          onClick = { onChangeState(item) },
                          role = Role.RadioButton
                      )
                      .padding(10.dp)){
                  Spacer(modifier = Modifier.width(10.dp))
                  RadioButton(selected = isSelectedItem(item), onClick = null)
                  Text(text = item)
              }
          }
      }
  }
}

@Composable
fun SubmitButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit) {
    Button(modifier = modifier.height(AppTheme.dimens.normalButtonHeight)
        .fillMaxWidth(),
        enabled = !isLoading,
        onClick = onClick) {
        if (isLoading) {
            Progressbar(isLoading)
        } else {
            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}


@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean) {

    Row(
        Modifier
            .clickable(onClick = onCheckChanged)
            .padding(4.dp)) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(modifier = Modifier.size(6.dp))
        Text(label)
    }
}