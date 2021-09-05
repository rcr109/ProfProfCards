package rcr.projects.profcards.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rcr.projects.profcards.model.Cartao

@Database(entities = [Cartao::class], version = 1, exportSchema = false)
abstract class CartaoDatabase: RoomDatabase() {

    abstract fun cartaoDao(): CartaoDao

    companion object{
        private var INSTANCE: CartaoDatabase? = null

        fun getDatabase(context: Context): CartaoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartaoDatabase::class.java,
                    "cartao_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}