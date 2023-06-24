package rs.ac.metropolitan.cs330pz.domain.model

import androidx.room.Entity
import androidx.room.TypeConverters
import rs.ac.metropolitan.cs330pz.data.remote.converters.StringListTypeConverter
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto

@Entity
data class CocktailDetail (
    val description: String,
    val id: Int,
    @TypeConverters(StringListTypeConverter::class)
    val ingredients: List<String>,
    val name: String,
    val origin: String,
    val strength: Int,
    @TypeConverters(StringListTypeConverter::class)
    val tags: List<String>,
    val imageUrl: String
)