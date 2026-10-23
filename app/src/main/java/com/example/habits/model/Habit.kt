package com.example.habits.model

data class Habit(
    val id: Int,
    val emoji: String,
    val title: String,
    val isDone: Boolean = false
)
