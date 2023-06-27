package rs.ac.metropolitan.cs330pz

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.get_cocktail_by_id.GetCocktailByIdFromDatabaseUseCase

class GetCocktailByIdFromDatabaseUseCaseTest {

    private lateinit var getCocktailByIdUseCase: GetCocktailByIdFromDatabaseUseCase
    private lateinit var fakeCocktailDatabaseRepository: FakeCocktailDatabaseRepositoryImpl

    @Before
    fun setUp() {
        fakeCocktailDatabaseRepository = FakeCocktailDatabaseRepositoryImpl()
        getCocktailByIdUseCase = GetCocktailByIdFromDatabaseUseCase(fakeCocktailDatabaseRepository)
    }

    @Test
    fun shouldReturnCocktailDetailWithSpecifiedID() = runTest {
        // Arrange
        val cocktailId = 40
        val expectedCocktail = CocktailDetail("desc", 40, listOf("ing1", "ing2", "ing3"), "CocktailTest1", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", false)
        fakeCocktailDatabaseRepository.insertNewCocktail(expectedCocktail)
        // Act
        val actualCocktail = getCocktailByIdUseCase(cocktailId)

        // Assert
        assertEquals(expectedCocktail, actualCocktail)
    }
}
