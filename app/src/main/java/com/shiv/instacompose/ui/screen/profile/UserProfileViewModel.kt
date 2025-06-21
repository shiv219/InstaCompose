package com.shiv.instacompose.ui.screen.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shiv.instacompose.core.DispatcherProvider
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    val posts =  useCase.getUsersPost().cachedIn(viewModelScope)

    val selectedTab = mutableStateOf(ProfileTab.Posts)

    val _isRefreshing = MutableStateFlow(false)

    fun refreshUserProfileDetails(){
        viewModelScope.launch(defaultDispatchers.io) {
        useCase.refreshUserProfile()
        }
    }
    fun refreshUserStory(){
        viewModelScope.launch(defaultDispatchers.io) {
            useCase.refreshUserStory()
        }
    }

    fun getUsersProfile() {
        viewModelScope.launch(defaultDispatchers.io) {
            useCase.getUserProfile().collectLatest { collected->
                collected?.let {
                    userProfileState_.value = UiState.Success(it)
                }
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
    fun onPullToRefreshTrigger() {
        _isRefreshing.value =  true
        viewModelScope.launch {
            refreshUserProfileDetails()
            refreshUserStory()
            /**
             * Added delay to simulate the behaviour of api call
             */
            delay(1000)
            _isRefreshing.value = false
        }
    }

}