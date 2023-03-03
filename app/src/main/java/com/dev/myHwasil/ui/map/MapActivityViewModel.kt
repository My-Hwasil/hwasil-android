package com.dev.myHwasil.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.myHwasil.common.result.Results
import com.dev.myHwasil.common.result.asResult
import com.dev.myHwasil.data.toilet.repository.ToiletRepository
import com.dev.myHwasil.domain.toilet.Toilet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapActivityViewModel @Inject constructor(
    private val toiletRepo: ToiletRepository
) : ViewModel() {
    private val _toiletsFlow = MutableStateFlow<Results<List<Toilet>>>(Results.Loading)
    val toiletsFlow: StateFlow<Results<List<Toilet>>> = _toiletsFlow.asStateFlow()

    fun fetchToiletInfoByAddress(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toiletRepo.findNearByAddress(
                address = address
            ).asResult()
                .collect { _toiletsFlow.emit(it) }
        }
    }
}