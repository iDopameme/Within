package com.rjwalker.within.feature.tasks

import androidx.lifecycle.ViewModel
import com.rjwalker.within.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksScreenViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
): ViewModel() {

}