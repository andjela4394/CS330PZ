package rs.ac.metropolitan.cs330pz

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330pz.FakeCocktailDatabaseRepositoryImpl
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_favorite_cocktail.GetFavoriteCocktailFromDatabaseUseCase

class GetFavoriteCocktailFromDatabaseUseCaseTest {

    private lateinit var getFavoriteCocktailUseCase: GetFavoriteCocktailFromDatabaseUseCase
    private lateinit var fakeCocktailDatabaseRepository: FakeCocktailDatabaseRepositoryImpl

    @Before
    fun setUp() {
        fakeCocktailDatabaseRepository = FakeCocktailDatabaseRepositoryImpl()
        getFavoriteCocktailUseCase = GetFavoriteCocktailFromDatabaseUseCase(fakeCocktailDatabaseRepository)
    }

    @Test
    fun invoke_shouldReturnFavoriteCocktailDetailsFromRepository() = runTest {
        // Arrange
        val expectedSize = 2
        val expectedFirst = CocktailDetail("desc", 40, listOf("ing1", "ing2", "ing3"), "CocktailTest1", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", true)
        val expectedSecond = CocktailDetail ("desc", 41, listOf("ing1", "ing2", "ing3"), "CocktailTest2", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", true)

        val favoriteCocktails = listOf(expectedFirst, expectedSecond)
        fakeCocktailDatabaseRepository.setCocktailDetails(favoriteCocktails)

        // Act
        val actualResultList = getFavoriteCocktailUseCase().first()

        // Assert
        assertEquals(expectedSize, actualResultList.size)
        assertEquals(expectedFirst, actualResultList[0])
        assertEquals(expectedSecond, actualResultList[1])
    }
}
