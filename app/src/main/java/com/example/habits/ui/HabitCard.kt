package com.example.habits.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habits.model.Habit

private val Green = Color(0xFF34C759)
private val GreenBg = Color(0xFFE8F7EC)
private val GreenText = Color(0xFF1E8E3E)
private val GrayBg = Color(0xFFF2F2F7)
private val GrayText = Color(0xFF8E8E93)
private val GrayBorder = Color(0xFFD1D1D6)

@Composable
fun HabitCard(
    habit: Habit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE5E5EA))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Кружечок-чекбокс
            if (habit.isDone) {
                Box(
                    modifier = Modifier.size(32.dp).clip(CircleShape).background(Green),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
            } else {
                Box(modifier = Modifier.size(32.dp).clip(CircleShape).border(3.dp, GrayBorder, CircleShape))
            }

            Text(
                text = "${habit.emoji} ${habit.title}",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )

            // Бейдж статусу
            val (bg, fg, label) = if (habit.isDone)
                Triple(GreenBg, GreenText, "✓ готово")
            else
                Triple(GrayBg, GrayText, "пропущено")

            Text(
                text = label,
                color = fg,
                fontSize = 14.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            )
        }
    }
}
