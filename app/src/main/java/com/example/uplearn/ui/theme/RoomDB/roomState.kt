package com.example.uplearn.ui.theme.RoomDB

data class userInfoState(
    val UserInfo: List<userInfo> = emptyList(),
    val userName: String = "",
    val course:String = "",
    val isAddinguserInfo: Boolean = false,
    val sortType: SortType = SortType.USER_NAME
)

data class subjectState(
    val Subject: List<subject> = emptyList(),
    val subjectName: String = "",
    val difficulty:String = "",
    val priorityLevel:String = "",
    val isAddingSubject: Boolean = false,
    val sortType2: SortType2 = SortType2.SUBJECT_NAME
)

data class studySessionState(
    val studySession: List<studySession> = emptyList(),
    val duration:String = "",
    val date:String = "",
    val isAddingStudySession: Boolean = false,
    val sortType3: SortType3 = SortType3.DURATION_NAME
)


