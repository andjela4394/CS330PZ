package rs.ac.metropolitan.cs330pz.domain.model

import rs.ac.metropolitan.cs330pz.data.remote.dto.CocktailDto

data class Cocktails (
    val drinks: List<CocktailDto>
)