package rs.ac.metropolitan.cs330pz.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.ac.metropolitan.cs330pz.common.Constants
import rs.ac.metropolitan.cs330pz.data.local.CocktailDetailDatabase
import rs.ac.metropolitan.cs330pz.data.local.dao.CocktailDetailDao
import rs.ac.metropolitan.cs330pz.data.remote.CocktailApi
import rs.ac.metropolitan.cs330pz.data.repository.CocktailDatabaseRepositoryImpl
import rs.ac.metropolitan.cs330pz.data.repository.CocktailRepositoryImpl
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCocktailApi(): CocktailApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(api: CocktailApi): CocktailRepository {
        return CocktailRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application):Context{
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideCocktailDetailDatabase(context: Context): CocktailDetailDatabase {
        return Room.databaseBuilder(
            context,
            CocktailDetailDatabase::class.java,
            "cocktail_detail_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCocktailDetailDao(database: CocktailDetailDatabase): CocktailDetailDao {
        return database.dao
    }


    @Provides
    @Singleton
    fun provideCocktailDatabaseRepository(db: CocktailDetailDatabase) : CocktailDatabaseRepository {
        return CocktailDatabaseRepositoryImpl(db.dao)
    }

}