package dev.vladimir.mobinttestproject.presentation.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.mobinttestproject.domain.CardsInteractor
import dev.vladimir.mobinttestproject.domain.models.Company
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardsInteractor: CardsInteractor,
) : ViewModel() {

    val companiesState: StateFlow<PagingData<Company>>
        get() = mutableCompaniesState
    private val mutableCompaniesState =
        MutableStateFlow<PagingData<Company>>(PagingData.empty())

    init {
        loadCompanies()
    }

    private fun loadCompanies() {
        viewModelScope.launch {
            cardsInteractor.getAllCompanies()
                .cachedIn(viewModelScope)
                .collect(mutableCompaniesState::emit)
        }
    }
}
