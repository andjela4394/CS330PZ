package rs.ac.metropolitan.cs330pz.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail


interface CocktailApi {


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

    @GET("/cocktails")
    suspend fun getCocktailsByNameAndTags(
        @Query("name_like") name: String = "",
        @Query("tags_like") tags: String = "",
        @Query("ingredients_like") ingredients: String = "",
        @Query("_page") page: Int = 1,
        @Query("name_regex_flags") flags: String = "i"
    ):List<CocktailDto>

    @GET("/cocktails")
    suspend fun getCocktailsByTag(
        @Query("tag_like") tags: String,
        @Query("_page") page: Int = 1,
        @Query("name_regex_flags") flags: String = "i"
    ):List<CocktailDto>

    @GET("/cocktails")
    suspend fun getCocktailsCountByNameAndTags(
        @Query("name_like") name: String = "",
        @Query("tags_like") tags: String = "",
        @Query("ingredients_like") ingredients: String = "",
        @Query("name_regex_flags") flags: String = "i"
    ): List<CocktailDto>

    @POST("/cocktails")
    suspend fun uploadNewCocktail(@Body newCocktail: CocktailDetail)
}