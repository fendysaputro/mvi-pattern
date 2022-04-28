package id.phephen.mvipattern.data.api

import id.phephen.mvipattern.data.model.User
import retrofit2.http.GET

/**
 * Created by Phephen on 27/04/2022.
 */
interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}