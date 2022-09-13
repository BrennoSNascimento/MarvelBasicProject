package com.example.projeto.projectmarvel.presentation.fragment.favorited

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projeto.projectmarvel.R
import com.example.projeto.projectmarvel.data.storage.SharedPreferences
import com.example.projeto.projectmarvel.databinding.FragmentFavoritedCharactersBinding
import com.example.projeto.projectmarvel.presentation.adapter.AdapterFavorited

class FavoritedCharactersFragment : Fragment() {

    private lateinit var binding: FragmentFavoritedCharactersBinding
    private lateinit var favoriteCharactersAdapter : AdapterFavorited


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFavoritedCharactersBinding.inflate(inflater,container,false)

        setupAdapter()
        setupView()

        return binding.root
    }

    private fun setupAdapter(){
        favoriteCharactersAdapter = AdapterFavorited()
        binding.rvFavoritedCharacters.adapter = favoriteCharactersAdapter

        binding.rvFavoritedCharacters.apply {
            favoriteCharactersAdapter.onItemClickListener = {
                SharedPreferences(requireContext()).deleteMovie(it.id)
                setupView()
            }
        }
    }

    fun setupView(){
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_favoritedCharactersFragment_to_homeFragment)
        }

        favoriteCharactersAdapter.replaceItems(SharedPreferences(requireContext()).getList())
    }

}