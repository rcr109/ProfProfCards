package rcr.projects.profcards.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rcr.projects.profcards.data.CartaoDatabase
import rcr.projects.profcards.repository.CartaoRepository
import rcr.projects.profcards.model.Cartao

class CartaoViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Cartao>>
    private val repository: CartaoRepository

    init {
        val cartaoDao = CartaoDatabase.getDatabase(application).cartaoDao()
        repository = CartaoRepository(cartaoDao)
        readAllData = repository.readAllData
    }

    fun addCartao(cartao: Cartao){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCartao(cartao)
        }
    }

    fun updateCartao(cartao: Cartao){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCartao(cartao)
        }
    }

    fun deleteCartao(cartao: Cartao){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCartao(cartao)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }



}