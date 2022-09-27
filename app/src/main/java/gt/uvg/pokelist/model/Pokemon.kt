package gt.uvg.pokelist.model

import com.squareup.moshi.Json

data class Pokemon(
    val url: String,
    val name: String
) {
    private val urlSplit = url.split("/");
    val id: Int = Integer.valueOf(urlSplit[urlSplit.size-2])

    val imageUrlFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val imageUrlBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    val imageUrlShinnyFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    val imageUrlShinnyBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}

data class PokemonResponse(@Json(name="results") val result : List<Pokemon>)