package id.phephen.mvipattern.data.api

import id.phephen.mvipattern.data.model.User

/**
 * Created by Phephen on 27/04/2022.
 */
interface ApiHelper {
    suspend fun getUsers(): List<User>
}