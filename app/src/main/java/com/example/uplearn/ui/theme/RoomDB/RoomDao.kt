package com.example.uplearn.ui.theme.RoomDB
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface userInfoDao {
    @Query("SELECT * FROM userInfo ORDER BY username ASC ")
    fun getUserInfoOrderedByUsername(): Flow<List<userInfo>>

    @Query("SELECT * FROM userInfo ORDER BY course ASC ")
    fun getUserInfoOrderedByCourse(): Flow<List<userInfo>>

    @Upsert
    suspend fun addUserInfo(UserInfo: userInfo)
    @Delete
    suspend fun deleteUserInfo(UserInfo: userInfo)
}

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject ORDER BY subjectName ASC ")
    fun getSubjectOrderedBySubjectName(): Flow<List<subject>>

    @Query("SELECT * FROM subject ORDER BY difficulty ASC ")
    fun getSubjectOrderedByDifficulty(): Flow<List<subject>>

    @Query("SELECT * FROM subject ORDER BY priorityLevel ASC ")
    fun getSubjectOrderedByPriorityLevel(): Flow<List<subject>>


    @Upsert
    suspend fun addSubject(Subject: subject)
    @Delete
    suspend fun deleteSubject(Subject: subject)

}
    @Dao
    interface studySessionDao {
        @Query("SELECT * FROM studySession ORDER BY duration ASC ")
        fun getStudySessionOrderedByDuration(): Flow<List<studySession>>

        @Query("SELECT * FROM studySession ORDER BY date ASC ")
        fun getStudySessionOrderedByDate(): Flow<List<studySession>>

        @Upsert
        suspend fun addStudySession(StudySession: studySession)
        @Delete
        suspend fun deleteStudySession(StudySession: studySession)
    }