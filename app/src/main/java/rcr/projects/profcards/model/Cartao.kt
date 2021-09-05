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
    val titulo: String,
    val mensagem: String,
    val data: String,
    val status: String,
    val dono: String
) : Parcelable