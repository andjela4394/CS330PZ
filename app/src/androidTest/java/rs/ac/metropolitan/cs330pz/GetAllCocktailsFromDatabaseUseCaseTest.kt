package rs.ac.metropolitan.cs330pz

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.repository.CocktailDatabaseRepository
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_all_cocktail.GetAllCocktailDatabaseUseCase

class GetAllCocktailDatabaseUseCaseTest {

    private lateinit var getAllCocktailDatabaseUseCase: GetAllCocktailDatabaseUseCase
    private lateinit var fakeCocktailDatabaseRepository: FakeCocktailDatabaseRepositoryImpl

    @Before
    fun setUp() {
        fakeCocktailDatabaseRepository = FakeCocktailDatabaseRepositoryImpl()
        getAllCocktailDatabaseUseCase = GetAllCocktailDatabaseUseCase(fakeCocktailDatabaseRepository)
    }

    @Test
    fun invoke_shouldReturnAllCocktailDetailsFromRepository() = runBlockingTest {
        // Arrange
        val expectedList = listOf(
            CocktailDetail("desc", 40, listOf("ing1", "ing2", "ing3"), "CocktailTest1", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", false),
            CocktailDetail ("desc", 41, listOf("ing1", "ing2", "ing3"), "CocktailTest2", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", false),
            CocktailDetail ("desc", 42, listOf("ing1", "ing2", "ing3"), "CocktailTest3", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", false)
        )
        fakeCocktailDatabaseRepository.setCocktailDetails(expectedList)

        // Act
        val actualList = getAllCocktailDatabaseUseCase().first()

        // Assert
        assertEquals(expectedList.size, actualList.size)
        assertEquals(expectedList, actualList)
    }
}
