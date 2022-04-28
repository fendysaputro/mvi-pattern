package id.phephen.mvipattern.data.api

import id.phephen.mvipattern.data.model.User

/**
 * Created by Phephen on 27/04/2022.
 */
class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

}