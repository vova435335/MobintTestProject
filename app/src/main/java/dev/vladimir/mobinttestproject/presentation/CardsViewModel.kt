package dev.vladimir.mobinttestproject.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vladimir.mobinttestproject.domain.CardsInteractor
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val cardsInteractor: CardsInteractor
) : ViewModel()
