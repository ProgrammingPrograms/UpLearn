package com.example.uplearn.ui.theme.RoomDB

import androidx.room.Database


@Database(entities = [userInfo::class], version = 1)
abstract class roomUserInfoDatabase {
    companion object {
        const val NAME = "userInfo_DB"
    }
    abstract fun getuserInfoDao(): userInfoDao
}

@Database(entities = [subject::class], version = 1)
abstract class roomsubjectDatabase {
    companion object {
        const val NAME = "subject_DB"
    }
    abstract fun getSubjectDao(): SubjectDao
}

@Database(entities = [studySession::class], version = 1)
abstract class roomStudySessionDatabase {
    companion object {
        const val NAME = "studySession_DB"
    }
    abstract fun getStudySessionDao(): studySessionDao
}
