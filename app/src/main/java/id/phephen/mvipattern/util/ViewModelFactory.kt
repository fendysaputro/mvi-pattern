package id.phephen.mvipattern.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.phephen.mvipattern.data.api.ApiHelper
import id.phephen.mvipattern.data.repository.MainRepository
import id.phephen.mvipattern.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Phephen on 27/04/2022.
 */
class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}