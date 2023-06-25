package rs.ac.metropolitan.cs330pz.presentation.nav_bar.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import rs.ac.metropolitan.cs330pz.presentation.nav_bar.CocktailNavBarViewModel
import rs.ac.metropolitan.cs330pz.presentation.cocktail_list.CocktailListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailSearchDialog (
    navViewModel: CocktailNavBarViewModel = hiltViewModel(),
    viewModel:CocktailListViewModel = hiltViewModel()
){


    var selectedTags by remember { mutableStateOf(emptyList<String>()) }


    var name by remember { mutableStateOf(TextFieldValue("")) }
    var ingredient by remember { mutableStateOf(TextFieldValue("")) }
    val tags = listOf(
        "Alcoholic",
        "Classic",
        "Strong",
        "Citrus",
        "Bitter",
        "Mint",
        "Tropical",
        "Creamy",
        "Sour",
        "Sweet",
        "Tiki",
        "Exotic",
        "Brunch",
        "Spicy",
        "Simple",
        "Refreshing",
        "Fruity",
        "Mixed",
        "Sparkling",
        "Non-alcoholic",
        "Tangy"
    )

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(4.dp),
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { viewModel.closeDialog()
                            navViewModel.closeDialog()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Search", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                TextField(
                    value = name,
                    onValueChange = { newText ->
                        name = newText
                    },
                    label = { Text("Cocktail name") },
                    placeholder = { Text("Enter the cocktail name") },
                )
                TextField(
                    value = ingredient,
                    onValueChange = { newText ->
                        ingredient = newText
                    },
                    label = { Text("Cocktail ingredient") },
                    placeholder = { Text("Enter the cocktail ingredient") },
                )
            }

            item {
                Column {
                    val rows = (tags.size + 1) / 2
                    val columns = 2

                    val chunkedTags = tags.chunked(columns)

                    chunkedTags.forEach { rowTags ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            rowTags.forEach { tag ->
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = selectedTags.contains(tag),
                                        onCheckedChange = { isChecked ->
                                            if (isChecked) {
                                                selectedTags = selectedTags + tag
                                            } else {
                                                selectedTags = selectedTags - tag
                                            }
                                        }
                                    )
                                    Text(
                                        text = tag,
                                        modifier = Modifier.padding(start = 4.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item {
                Button(onClick = {
                    val selectedTagsString = selectedTags.joinToString(",")
                    viewModel.getSearchCount()
                    viewModel.searchCocktail(
                        tags = selectedTagsString, name = name.text, ingredients = ingredient.text
                    )
                    navViewModel.closeDialogSearch()

                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}