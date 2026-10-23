package com.example.habits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habits.viewmodel.HabitViewModel

@Composable
fun HabitScreen(viewModel: HabitViewModel = viewModel()) {
    val habits by viewModel.habits.collectAsStateWithLifecycle()
    val doneCount by viewModel.doneCount.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF2F2F7))) {

        // Заголовок з реактивним лічильником
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .statusBarsPadding()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text("🌱 Звички", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xFF8E8E93))) { append("Виконано сьогодні: ") }
                    withStyle(SpanStyle(color = Color(0xFF34C759), fontWeight = FontWeight.Bold)) {
                        append("$doneCount з ${viewModel.totalCount}")
                    }
                },
                fontSize = 18.sp
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(habits, key = { it.id }) { habit ->
                HabitCard(
                    habit = habit,
                    onClick = { viewModel.toggleHabit(habit.id) }
                )
            }
        }

        Button(
            onClick = viewModel::resetDay,
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .height(56.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
        ) {
            Text("Скинути день", fontSize = 18.sp, color = Color.White)
        }
    }
}