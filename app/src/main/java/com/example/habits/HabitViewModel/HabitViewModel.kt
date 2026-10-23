package com.example.habits.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habits.model.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HabitViewModel : ViewModel() {

    // Початковий набір звичок — val, структурно не змінюється
    private val initialHabits: List<Habit> = listOf(
        Habit(1, "🏃", "Пробіжка", isDone = true),
        Habit(2, "💧", "Випити 2л води", isDone = true),
        Habit(3, "📖", "Читання 20 хв", isDone = true),
        Habit(4, "🧘", "Медитація", isDone = true),
        Habit(5, "✏️", "Щоденник"),
        Habit(6, "🎸", "Гітара 15 хв")
    )

    private val _habits = MutableStateFlow(initialHabits)
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

    // Похідний StateFlow — лічильник виконаних звичок
    val doneCount: StateFlow<Int> = _habits
        .map { list -> list.count { h -> h.isDone } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = initialHabits.count { it.isDone }
        )

    val totalCount: Int = initialHabits.size

    fun toggleHabit(id: Int) {
        _habits.update { list ->
            list.map { if (it.id == id) it.copy(isDone = !it.isDone) else it }
        }
    }

    fun resetDay() {
        _habits.update { list -> list.map { it.copy(isDone = false) } }
    }
}
