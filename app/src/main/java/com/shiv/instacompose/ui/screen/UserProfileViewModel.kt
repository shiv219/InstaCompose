package com.shiv.instacompose.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiv.instacompose.core.DispatcherProvider
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * avoiding here Intent and Action sealed classes for simplicity we can add these later
 * using State for managing the ui state
 */
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val useCase: UserProfileUseCase,
    private val defaultDispatchers: DispatcherProvider
) : ViewModel() {
    private val userProfileState_ = MutableStateFlow<UiState<UserProfile>>(UiState.Loading)
    val userProfileState = userProfileState_.asStateFlow()

    private val userStoryState_ = MutableStateFlow<UiState<List<UsersStory>>>(UiState.Loading)
    val usersStoryState = userStoryState_.asStateFlow()

    val posts =  useCase.getUsersPost().flowOn(defaultDispatchers.io)

    fun refreshUserProfileDetails(){
        useCase.refreshUserProfile()
    }
    fun refreshUserStory(){
        useCase.refreshUserStory()
    }

    fun getUsersProfile() {
        viewModelScope.launch(defaultDispatchers.io) {
            useCase.getUserProfile().collectLatest {
                userProfileState_.value = UiState.Success(it)
            }
        }
    }
    fun getUsersStory() {
        viewModelScope.launch(defaultDispatchers.io) {
            useCase.getUserStory().collectLatest {
                userStoryState_.value = UiState.Success(it)
            }
        }
    }

}