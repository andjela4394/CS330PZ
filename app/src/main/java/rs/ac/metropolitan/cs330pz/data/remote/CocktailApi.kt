package rs.ac.metropolitan.cs330pz.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailsDto


interface CocktailApi {


    @GET("search.php")
    suspend fun searchCocktailsByName(@Query("s") name: String): CocktailsDto

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") id: String): CocktailDto

    @GET("filter.php")
    suspend fun filterCocktailsByCategory(@Query("c") category: String): CocktailsDto


}