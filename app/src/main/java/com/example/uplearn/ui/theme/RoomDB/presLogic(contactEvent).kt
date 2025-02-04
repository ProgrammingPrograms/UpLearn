package com.example.uplearn.ui.theme.RoomDB

sealed interface ContactEventUserInfo{
    object saveUserInfo: ContactEventUserInfo
    data class setUsername(val username:String): ContactEventUserInfo
    data class setCourse(val course: String): ContactEventUserInfo

    object ShowDialog: ContactEventUserInfo
    object HideDialog: ContactEventUserInfo

    data class SortUserInfo(val sortType: SortType): ContactEventUserInfo
    data class DeleteUserInfo(val UserInfo: userInfo): ContactEventUserInfo

}
sealed interface ContactEventSubject {
    object saveSubject: ContactEventSubject
    data class setSubjectName(val subjectName:String): ContactEventSubject
    data class setSubjectDifficulty(val subjectDifficulty: String): ContactEventSubject
    data class setSubjectPriorityLevel(val subjectPriorityLevel: String): ContactEventSubject

    object ShowDialog: ContactEventSubject
    object HideDialog: ContactEventSubject

    data class SortSubject(val sortType2: SortType2): ContactEventSubject
    data class DeleteSubject(val Subject: subject): ContactEventSubject
}
sealed interface ContactStudySession{
    object saveStudySession: ContactStudySession
    data class setStudySessionDuration(val StudySessionDuration: String): ContactStudySession
    data class setStudySessionDate(val StudySessionDate: String): ContactStudySession

    object ShowDialog3: ContactStudySession
    object HideDialog3: ContactStudySession

    data class SortStudySession(val sortType3: SortType3): ContactStudySession
    data class DeleteStudySession(val StudySession: studySession): ContactStudySession

}