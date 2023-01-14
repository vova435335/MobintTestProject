package dev.vladimir.mobinttestproject.presentation.cards

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.mobinttestproject.R
import dev.vladimir.mobinttestproject.domain.CardsInteractor
import dev.vladimir.mobinttestproject.domain.models.Company
import dev.vladimir.mobinttestproject.presentation.common.StringProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardsInteractor: CardsInteractor,
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val mutableCompaniesState =
        MutableStateFlow<PagingData<Company>>(PagingData.empty())
    val companiesState: StateFlow<PagingData<Company>> = mutableCompaniesState

    private val mutableErrorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = mutableErrorEvent

    private val mutableShowCompanyInfoEvent = MutableSharedFlow<String>()
    val showCompanyInfoEvent: SharedFlow<String> = mutableShowCompanyInfoEvent

    init {
        loadCompanies()
    }

    fun handleError(throwable: Throwable) {
        viewModelScope.launch {
            if (throwable is HttpException) {
                when (throwable.code()) {
                    CardsErrors.ERROR_400.code -> {
                        mutableErrorEvent.emit(throwable.message())
                    }
                    CardsErrors.ERROR_401.code -> {
                        mutableErrorEvent.emit(stringProvider.getString(R.string.error_message_401))
                    }
                    CardsErrors.ERROR_500.code -> {
                        mutableErrorEvent.emit(stringProvider.getString(R.string.error_message_500))
                    }
                }
            }
        }
    }

    fun showCompanyInfo(@StringRes buttonNameRes: Int, companyId: String) {
        viewModelScope.launch {
            mutableShowCompanyInfoEvent.emit(
                stringProvider.getString(
                    R.string.company_info_dialog_message,
                    stringProvider.getString(buttonNameRes),
                    companyId
                )
            )
        }
    }

    private fun loadCompanies() {
        viewModelScope.launch {
            cardsInteractor.getPagingAllCompanies()
                .cachedIn(viewModelScope)
                .collect(mutableCompaniesState::emit)
        }
    }
}
