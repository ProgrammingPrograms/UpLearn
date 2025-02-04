package com.example.uplearn.ui.theme.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class userInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val course: String
)

@Entity
data class subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int = 0,
    val subjectName: String,
    val difficulty: String,
    val priorityLevel: String,

)

@Entity
data class studySession(
    @PrimaryKey(autoGenerate = true)
    val studySessionId: Int = 0,
    val duration: String,
    val date: String

)
//show list of subjects by user
//study session contain object subjects name
//duration and date of session