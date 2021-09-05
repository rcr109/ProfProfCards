package rcr.projects.profcards.repository

import androidx.lifecycle.LiveData
import rcr.projects.profcards.data.CartaoDao
import rcr.projects.profcards.model.Cartao

class CartaoRepository(private val cartaoDao: CartaoDao) {

    val readAllData: LiveData<List<Cartao>> = cartaoDao.readAllData()

    suspend fun addCartao(cartao: Cartao){
        cartaoDao.addCartao(cartao)
    }

    suspend fun updateCartao(cartao: Cartao){
        cartaoDao.updateCartao(cartao)
    }

    suspend fun deleteCartao(cartao: Cartao){
        cartaoDao.deleteCartao(cartao)
    }

    suspend fun deleteAll(){
        cartaoDao.deleteAll()
    }
}