package rs.ac.metropolitan.cs330pz.presentation.cocktail_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun CocktailDetailScreen(
    viewModel: CocktailDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value


    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            state.cocktail?.let { cocktail ->
                Log.d("CocktailDetailScreen", "Cocktail: $cocktail ucitava koktel nije ovde greska")
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
//                    Image(
//                        painter = rememberImagePainter(cocktail.imageUrl),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(200.dp)
//                            .align(Alignment.CenterHorizontally)
//                            .clip(shape = RoundedCornerShape(8.dp))
//                            .border(
//                                width = 1.dp,
//                                color = Color.Gray,
//                                shape = RoundedCornerShape(8.dp)
//                            )
//                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = cocktail.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cocktail.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ingredients:",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    cocktail.ingredients.forEach { ingredient ->
                        Text(
                            text = ingredient,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Measurements:",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
//                    cocktail.measurements.forEach { measurement ->
//                        Text(
//                            text = measurement,
//                            style = MaterialTheme.typography.bodyMedium,
//                            modifier = Modifier.padding(start = 8.dp)
//                        )
//                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // Display more cocktail information as needed
                }
            }
        }
    }
}
