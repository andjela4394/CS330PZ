package rs.ac.metropolitan.cs330pz.presentation.cocktail_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import rs.ac.metropolitan.cs330pz.presentation.Screen
import rs.ac.metropolitan.cs330pz.presentation.cocktail_favorite.CocktailFavoriteViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CocktailDetailScreen(
    viewModel: CocktailDetailViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier.padding(top = 20.dp),
) {
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    var isFavorite = viewModel.state.value.isFavorite
    var expanded by remember { mutableStateOf(false) }

    viewModel.addCocktailToDatabase()

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Cocktail List",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )

            IconButton(
                modifier = Modifier
                    .scale(1.5f)
                    .align(Alignment.BottomEnd),
                onClick = {
                    coroutineScope.launch {
                        viewModel.check()
                    }
                   //viewModel.favourite()

                }){
                Icon(
                    imageVector = if (isFavorite == true) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                state.cocktail?.let { cocktail ->
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                    Image(
                        painter = rememberImagePainter(cocktail.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .aspectRatio(3f / 3f)
                    )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = cocktail.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = cocktail.description,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .height(if (expanded) 200.dp else 50.dp)
                                .clickable { expanded = !expanded },
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Ingredients:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        cocktail.ingredients.forEach { ingredient ->
                            Text(
                                text = ingredient,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 8.dp),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = "Tags:",
//                            style = MaterialTheme.typography.bodyLarge,
//                            fontWeight = FontWeight.Bold,
//                            color = MaterialTheme.colorScheme.onPrimaryContainer
//                        )
//                        cocktail.tags.forEach { tag ->
//                            Text(
//                                text = tag,
//                                style = MaterialTheme.typography.bodyMedium,
//                                modifier = Modifier.padding(start = 8.dp),
//                                color = MaterialTheme.colorScheme.onPrimaryContainer
//                            )
//                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .border(1.dp, color = Color.Transparent)
                                ) {
                                    val rows = mutableListOf<MutableList<String>>(mutableListOf())
                                    var currentRowCharacterCount = 0

                                    cocktail.tags.forEach { tag ->
                                        val tagCharacterCount = tag.length

                                        if (currentRowCharacterCount + tagCharacterCount > 20) {
                                            rows.add(mutableListOf(tag))
                                            currentRowCharacterCount = tagCharacterCount
                                        } else {
                                            val lastRowIndex = rows.lastIndex
                                            rows[lastRowIndex].add(tag)
                                            currentRowCharacterCount += tagCharacterCount
                                        }
                                    }

                                    rows.forEach { rowTags ->
                                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                                            rowTags.forEachIndexed { index, tag ->
                                                Box(
                                                    modifier = Modifier
                                                        .background(
                                                            MaterialTheme.colorScheme.primary.copy(
                                                                alpha = 0.1f
                                                            ), shape = CircleShape
                                                        )
                                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                                        .padding(end = 8.dp)
                                                ) {
                                                    Text(
                                                        text = tag,
                                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                        style = MaterialTheme.typography.bodyLarge,
                                                        modifier = Modifier.padding(horizontal = 4.dp)
                                                    )
                                                }

                                                // Add spacing between tags
                                                if (index < rowTags.size - 1) {
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

