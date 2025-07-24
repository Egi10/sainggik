package id.buaja.sainggik.core.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import id.buaja.sainggik.core.designsystem.theme.SainggikTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun <T> SwipeableListItem(
    item: T,
    onEdit: (T) -> Unit,
    onDelete: (T) -> Unit,
    content: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val actionWidth = with(density) {
        160.dp.toPx()
    } // Lebar area aksi
    val threshold = actionWidth / 3 // Threshold untuk membuka

    val offsetX = remember {
        Animatable(0f)
    }
    val scope = rememberCoroutineScope()

    // State untuk menentukan apakah sedang terbuka
    var isRevealed by remember { mutableStateOf(false) }

    // Fungsi untuk menutup swipe
    val closeSwipe = {
        scope.launch {
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = tween(300)
            )
            isRevealed = false
        }
    }

    // Fungsi untuk membuka swipe
    val openSwipe = {
        scope.launch {
            offsetX.animateTo(
                targetValue = -actionWidth,
                animationSpec = tween(300)
            )
            isRevealed = true
        }
    }

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        // Background dengan tombol aksi
        SwipeBackground(
            modifier = Modifier
                .matchParentSize(),
            onEdit = {
                onEdit(item)
                closeSwipe()
            },
            onDelete = {
                onDelete(item)
                closeSwipe()
            }
        )

        // Konten utama yang bisa di-swipe
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            when {
                                // Jika drag ke kiri dan melebihi threshold, buka
                                offsetX.value <= -threshold && !isRevealed -> {
                                    openSwipe()
                                }
                                // Jika drag ke kanan dan sudah terbuka, tutup
                                offsetX.value > -threshold && isRevealed -> {
                                    closeSwipe()
                                }
                                // Jika tidak memenuhi kondisi, kembali ke posisi semula
                                !isRevealed -> {
                                    scope.launch {
                                        offsetX.animateTo(
                                            targetValue = 0f,
                                            animationSpec = tween(300)
                                        )
                                    }
                                }
                                // Jika sudah terbuka, kembali ke posisi terbuka
                                isRevealed -> {
                                    scope.launch {
                                        offsetX.animateTo(
                                            targetValue = -actionWidth,
                                            animationSpec = tween(300)
                                        )
                                    }
                                }
                            }
                        }
                    ) { _, dragAmount ->
                        scope.launch {
                            val newValue = (offsetX.value + dragAmount).coerceIn(-actionWidth, 0f)
                            offsetX.snapTo(newValue)
                        }
                    }
                }
        ) {
            content(item)
        }
    }
}

@Composable
private fun SwipeBackground(
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(SainggikTheme.colors.background),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SwipeActionButton(
            label = "Edit",
            icon = Icons.Default.Edit,
            backgroundColor = SainggikTheme.colors.primary,
            onClick = onEdit
        )
        SwipeActionButton(
            label = "Delete",
            icon = Icons.Default.Delete,
            backgroundColor = SainggikTheme.colors.error,
            onClick = onDelete
        )
    }
}

@Composable
private fun SwipeActionButton(
    label: String,
    icon: ImageVector,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = SainggikTheme.colors.onPrimary,
                modifier = Modifier.size(24.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            Text(
                text = label,
                color = SainggikTheme.colors.onPrimary,
                style = SainggikTheme.typography.label
            )
        }
    }
}