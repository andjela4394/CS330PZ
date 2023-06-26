package rs.ac.metropolitan.cs330pz.presentation.cocktail_history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import rs.ac.metropolitan.cs330pz.domain.model.CocktailDetail

@Composable
fun CocktailHistoryMainItem(
    cocktail: CocktailDetail,
    modifier: Modifier = Modifier,
    onItemClick: (CocktailDetail) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 18.dp
        ),
        modifier = modifier
            .clickable { onItemClick(cocktail) }
            .fillMaxSize()
            .width(280.dp)
            .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
            .clickable {
                onItemClick(cocktail)
            }, shape = MaterialTheme.shapes.large,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = cocktail.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .height(350.dp)
                .size(310.dp)
                .padding(5.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(20.dp))
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            modifier = Modifier
                .padding(2.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${cocktail.name}",
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Strength: ${cocktail.strength}",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = cocktail.tags.toList()
                    .take(3)
                    .filter { it.length < 12 }
                    .joinToString(", "),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            )
        }

    }
}