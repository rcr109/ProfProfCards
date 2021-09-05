package rcr.projects.profcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import rcr.projects.profcards.model.Cartao

@Dao
interface CartaoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCartao(cartao: Cartao)

    @Update
    suspend fun updateCartao(cartao: Cartao)

    @Delete
    suspend fun deleteCartao(cartao: Cartao)

    @Query("DELETE FROM cartoes")
    suspend fun deleteAll()

    @Query("SELECT * FROM cartoes order by id ASC")
    fun readAllData(): LiveData<List<Cartao>>

}