package id.buaja.sainggik.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SainggikModalBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { true }
    ),
    content: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 12.dp
    )
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = SainggikTheme.colors.background,
        content = {
            Box(
                modifier = Modifier
                    .padding(
                        paddingValues = contentPadding
                    )
            ) {
                content()
            }
        }
    )
}