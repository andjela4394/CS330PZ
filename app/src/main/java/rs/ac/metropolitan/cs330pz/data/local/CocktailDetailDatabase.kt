package rs.ac.metropolitan.cs330pz.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import rs.ac.metropolitan.cs330pz.data.local.converters.StringListTypeConverter
import rs.ac.metropolitan.cs330pz.data.local.dao.CocktailDetailDao
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

@Database(
    entities = [CocktailDetail::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListTypeConverter::class)
abstract class CocktailDetailDatabase :RoomDatabase(){

    abstract val dao: CocktailDetailDao

}