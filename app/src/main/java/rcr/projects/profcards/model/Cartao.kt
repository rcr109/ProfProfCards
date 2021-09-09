package rcr.projects.profcards.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cartoes")
data class Cartao (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val empresa: String,
    val profissao: String,
    val telefone: String,
    val email: String,
) : Parcelable