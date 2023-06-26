package rs.ac.metropolitan.cs330pz
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail
import rs.ac.metropolitan.cs330pz.domain.use_case.database_use_cases.add_cocktail.AddCocktailToDatabaseUseCase

class AddCocktailToDatabaseUseCaseTest {

    private lateinit var addCocktailToDatabaseUseCase: AddCocktailToDatabaseUseCase
    private lateinit var fakeCocktailDatabaseRepository: FakeCocktailDatabaseRepositoryImpl

    @Before
    fun setUp() {
        fakeCocktailDatabaseRepository = FakeCocktailDatabaseRepositoryImpl()
        addCocktailToDatabaseUseCase = AddCocktailToDatabaseUseCase(fakeCocktailDatabaseRepository)
    }

    @Test
    fun `testInsertCocktail_shouldInsertCocktailIntoRepository`() = runTest {
        // Arrange
        val cocktailDetail = CocktailDetail("desc", 40, listOf("ing1", "ing2", "ing3"), "CocktailTest", "Srb", 6, listOf("tag1", "tag2", "tag3"), "img.jpg", false)
        val expectedSize = 1
        val expectedFirst = cocktailDetail

        // Act
        addCocktailToDatabaseUseCase(cocktailDetail)
        val actualResultList = fakeCocktailDatabaseRepository.getAllCocktailDetail().first()

        // Assert
        assertEquals(expectedSize, actualResultList.size)
        assertEquals(expectedFirst, actualResultList[0])
    }
}
