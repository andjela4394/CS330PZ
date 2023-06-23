package rs.ac.metropolitan.cs330pz.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto


interface CocktailApi {

//
//    @GET("search.php")
//    suspend fun searchCocktailsByName(@Query("s") name: String): CocktailsDto
//
//    @GET("lookup.php")
//    suspend fun getCocktailById(@Query("i") idDrink: String): CocktailDto
//
//    @GET("filter.php")
//    suspend fun filterCocktailsByCategory(@Query("c") category: String): CocktailsDto

    @GET("/cocktails")
    suspend fun getAll(): List<CocktailDto>

    @GET("/cocktails/{id}")
    suspend fun getCocktailById(@Path("id") cocktailId: Int): CocktailDto

    @GET("/cocktails")
    suspend fun getCocktailsByName(
        @Query("name_like") name: String = "",
        @Query("_page") page: Int = 1,
        @Query("name_regex_flags") flags: String = "i"
    ):List<CocktailDto>

}