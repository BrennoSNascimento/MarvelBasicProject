package com.example.projeto.projectmarvel.presentation.fragment

import android.os.Bundle
import android.view.KeyCharacterMap.load
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.PointerIconCompat.load
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.projeto.projectmarvel.R
import com.example.projeto.projectmarvel.databinding.FragmentHomeBinding
import com.example.projeto.projectmarvel.domain.Results
import com.example.projeto.projectmarvel.presentation.adapter.AdapterHome
import com.example.projeto.projectmarvel.presentation.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.System.load

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var characterAdapter: AdapterHome
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.setSearch()
        setupObserver()
        setupAdapter()

        binding.tvTitle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoritedCharactersFragment)
        }

        return binding.root
    }

    private fun setupObserver() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            when (it) {
                Results.MarvelApiResults.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    viewModel.isLoading.value = true
                }

                is Results.MarvelApiResults.Failure -> {

                }

                is Results.MarvelApiResults.Success -> {
                    binding.progress.visibility = View.GONE
                    characterAdapter.updateItemsHome(it.characterList.data.results)

                }
            }
        }
    }

    private fun setupAdapter() {
        characterAdapter = AdapterHome(requireContext())
        layoutManager = GridLayoutManager(binding.rvCharacters.context, 2)
        binding.rvCharacters.adapter = characterAdapter
        binding.rvCharacters.layoutManager = layoutManager
        //binding.rvMovies.addOnScrollListener(this@HomeFragment.scrollListener)

        binding.rvCharacters.apply {
            characterAdapter.onItemClickListener = {
                val args = Bundle().apply {
                    putSerializable(ARG, it)
                }
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, args)
            }
        }
    }

    companion object {
        const val ARG = "Arg"
    }
}