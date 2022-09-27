package gt.uvg.pokelist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import gt.uvg.pokelist.R
import gt.uvg.pokelist.databinding.FragmentDetailBinding
import gt.uvg.pokelist.model.PokemonResponse
import gt.uvg.pokelist.repository.PokemonRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    companion object{
        val PokemonID = "pokemonId"
    }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var pokemonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{pokemonId = it.getInt(PokemonID)}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val client = PokemonRepository.apiService.fetchPokemon("100")
        client.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ){
                if (response.isSuccessful){
                    val pokemonList = response.body()?.result
                    val pokemon = pokemonList?.find{it.id == pokemonId}

                    Picasso.get().load(pokemon!!.imageUrlFront).into(binding.imageView2)
                    Picasso.get().load(pokemon.imageUrlBack).into(binding.imageView3)
                    Picasso.get().load(pokemon.imageUrlShinnyFront).into(binding.imageView4)
                    Picasso.get().load(pokemon.imageUrlShinnyBack).into(binding.imageView5)
                }
            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "a", Toast.LENGTH_LONG).show()
                Log.e("failed",""+t.message)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}