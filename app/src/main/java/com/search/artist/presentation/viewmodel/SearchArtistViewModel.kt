package com.search.artist.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.artist.data.model.searchArtist.SearchArtistResponse
import com.search.artist.data.util.Resource
import com.search.artist.domain.usecase.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchArtistViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase,
) : ViewModel() {
    private val _searchArtist = MutableStateFlow<Resource<SearchArtistResponse>>(Resource.Loading())
    val searchArtist = _searchArtist.asStateFlow()

    fun searchArtist(
        releaseTitle: String,
        artistName: String,
        page: Int,
        perPage: Int
    ) {
        viewModelScope.launch {
            _searchArtist.value = Resource.Loading()
            try {

                //  if (isInternetAvailable(app)) {
                val apiResult = searchArtistUseCase.execute(releaseTitle, artistName, page, perPage)
                _searchArtist.value = apiResult
                // } else {
                //_searchArtist.value = Resource.Error("No internet connection")
                //}
            } catch (e: Exception) {
                _searchArtist.value = Resource.Error(e.message.toString())
            }
        }
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}