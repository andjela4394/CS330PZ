package rs.ac.metropolitan.cs330pz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rs.ac.metropolitan.cs330pz.common.Constants
import rs.ac.metropolitan.cs330pz.data.remote.CocktailApi
import rs.ac.metropolitan.cs330pz.data.remote.CocktailRepositoryImpl
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
}