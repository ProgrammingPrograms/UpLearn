package com.example.uplearn.ui.theme.RoomDB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class userInfoViewModel(
    private val dao: userInfoDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.USER_NAME)
    private val _userInfo = _sortType.flatMapLatest { sortType ->
        when(sortType) {
            SortType.USER_NAME -> dao.getUserInfoOrderedByUsername()
            SortType.COURSE_NAME -> dao.getUserInfoOrderedByCourse()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
private val _state = MutableStateFlow(userInfoState())
    val state = combine(_state, _sortType,_userInfo){
        state, sortType, UserInfo -> state.copy(UserInfo = UserInfo,
            sortType = sortType)
    } .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), userInfoState())
    fun onEvent(event:ContactEventUserInfo){
        when(event){
            is ContactEventUserInfo.DeleteUserInfo -> {
                viewModelScope.launch {
                    dao.deleteUserInfo(event.UserInfo)
                }
            }
            ContactEventUserInfo.HideDialog -> {
                _state.update { it.copy(
                    isAddinguserInfo = false
                ) }
            }
            ContactEventUserInfo.ShowDialog ->  {
                _state.update { it.copy(isAddinguserInfo = true) }
            }
            is ContactEventUserInfo.SortUserInfo -> {_sortType.value = event.sortType}
            ContactEventUserInfo.saveUserInfo -> {
                val username = state.value.userName
                val course = state.value.course

                if(username.isBlank() || course.isBlank()){
                    return
                }
                val userInfo = userInfo(
                    username = username,
                    course = course,
                )
                viewModelScope.launch {
                    dao.addUserInfo(userInfo)
                }
                _state.update { it.copy(isAddinguserInfo = false,
                    userName = "",
                    course = "") }
            }
            is ContactEventUserInfo.setCourse ->  {
                _state.update { it.copy(course = event.course) }
            }
            is ContactEventUserInfo.setUsername -> {
                _state.update { it.copy(userName = event.username) }
            }
        }
    }

}
@OptIn(ExperimentalCoroutinesApi::class)
class subjectViewModel(
    private val dao: SubjectDao
): ViewModel() {
    private val _sortType2 = MutableStateFlow(SortType2.SUBJECT_NAME)
    private val _subject = _sortType2.flatMapLatest { sortType ->
        when(sortType) {
            SortType2.SUBJECT_NAME -> dao.getSubjectOrderedBySubjectName()
            SortType2.DIFFICULTY_NAME -> dao.getSubjectOrderedByDifficulty()
            SortType2.PRIORITY_LEVEL_NAME -> dao.getSubjectOrderedByPriorityLevel()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(subjectState())
    val state = combine(_state, _sortType2, _subject){
            state, sortType2, Subject -> state.copy(Subject = Subject,
        sortType2 = sortType2)
    } .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), subjectState())
    fun onEvent2(event:ContactEventSubject){
        when(event){
            is ContactEventSubject.DeleteSubject -> {
                viewModelScope.launch {
                    dao.deleteSubject(event.Subject)
                }
            }
            ContactEventSubject.HideDialog -> {
                _state.update { it.copy(
                    isAddingSubject = false
                ) }
            }
            ContactEventSubject.ShowDialog ->  {
                _state.update { it.copy(isAddingSubject = true) }
            }
            is ContactEventSubject.SortSubject -> {_sortType2.value = event.sortType2}
            ContactEventSubject.saveSubject -> {
                val subjectName = state.value.subjectName
                val difficulty = state.value.difficulty
                val priorityLevel = state.value.priorityLevel

            if(subjectName.isBlank() || difficulty.isBlank() ||priorityLevel.isBlank()){
                return
            }
                val subject = subject(
                subjectName = subjectName,
                difficulty = difficulty,
                    priorityLevel = priorityLevel
            )
            viewModelScope.launch {
                dao.addSubject(subject)
            }
                    _state.update { it.copy(isAddingSubject = false,
                subjectName = "",
                difficulty = "",
                        priorityLevel = "") } }
            is ContactEventSubject.setSubjectDifficulty ->  {
                _state.update { it.copy(difficulty = event.subjectDifficulty) }
            }
            is ContactEventSubject.setSubjectName -> {
                _state.update { it.copy(subjectName = event.subjectName) }
            }
            is ContactEventSubject.setSubjectPriorityLevel ->  {
                _state.update { it.copy(priorityLevel = event.subjectPriorityLevel) }
            }
        }
    }
}
@OptIn(ExperimentalCoroutinesApi::class)
class studySessionViewModel(
    private val dao: studySessionDao
): ViewModel() {
    private val _sortType3 = MutableStateFlow(SortType3.DURATION_NAME)
    private val _studySession = _sortType3.flatMapLatest { sortType ->
        when(sortType) {
            SortType3.DURATION_NAME -> dao.getStudySessionOrderedByDuration()
            SortType3.DATE_NAME -> dao.getStudySessionOrderedByDate()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(studySessionState())
    val state = combine(_state, _sortType3,_studySession){
            state, sortType3, studySession -> state.copy(studySession = studySession,
        sortType3 = sortType3)
    } .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), studySessionState())
    fun onEvent3(event:ContactStudySession){
        when(event){
            is ContactStudySession.DeleteStudySession -> {
                viewModelScope.launch {
                    dao.deleteStudySession(event.StudySession)
                }
            }
            ContactStudySession.HideDialog3 -> {
                _state.update { it.copy(
                    isAddingStudySession = false
                ) }
            }

            ContactStudySession.ShowDialog3 ->  {
                _state.update { it.copy(isAddingStudySession = true) }
            }
            is ContactStudySession.SortStudySession -> {_sortType3.value = event.sortType3}
            ContactStudySession.saveStudySession -> {
                val duration = state.value.duration
                val date = state.value.date

                if(duration.isBlank() || date.isBlank()){
                    return
                }
                val StudySession = studySession(
                    duration = duration,
                    date = date
                )
                viewModelScope.launch { dao.addStudySession(StudySession)
                }
                _state.update { it.copy(
                    isAddingStudySession = false,
                    duration = "",
                    date = ""
                ) }
            }
            is ContactStudySession.setStudySessionDate ->  {
                _state.update { it.copy(date = event.StudySessionDate) }
            }
            is ContactStudySession.setStudySessionDuration ->  {
                _state.update { it.copy(duration = event.StudySessionDuration) }
            }
        }
    }
}