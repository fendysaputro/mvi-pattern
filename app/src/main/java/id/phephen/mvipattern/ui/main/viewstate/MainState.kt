package id.phephen.mvipattern.ui.main.viewstate

import id.phephen.mvipattern.data.model.User

/**
 * Created by Phephen on 27/04/2022.
 */
sealed class MainState {

    object Idle: MainState()
    object Loading: MainState()
    data class Users(val user: List<User>): MainState()
    data class Error(val error: String?): MainState()

}