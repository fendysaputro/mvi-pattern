package id.phephen.mvipattern.data.repository

import id.phephen.mvipattern.data.api.ApiHelper

/**
 * Created by Phephen on 27/04/2022.
 */
class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}