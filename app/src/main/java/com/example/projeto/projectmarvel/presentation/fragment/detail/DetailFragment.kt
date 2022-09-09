package com.example.projeto.projectmarvel.presentation.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projeto.projectmarvel.R
import com.example.projeto.projectmarvel.data.model.ResultsMarvel
import com.example.projeto.projectmarvel.databinding.FragmentDetailBinding
import com.example.projeto.projectmarvel.domain.Results
import com.example.projeto.projectmarvel.presentation.adapter.AdapterComics
import com.example.projeto.projectmarvel.presentation.adapter.AdapterHome
import com.example.projeto.projectmarvel.presentation.fragment.HomeFragment.Companion.ARG
import com.example.projeto.projectmarvel.presentation.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var comicsAdapter: AdapterComics
    private lateinit var selectedCharacter: ResultsMarvel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setup()
        return binding.root
    }

    private fun setup() {
        selectedCharacter = arguments?.getSerializable(ARG) as ResultsMarvel
        setupView()
        setupObserver()
        setupAdapter()
    }

    private fun setupView() {
        viewModel.setSearch(selectedCharacter.id.toString())

        binding.tvTitle.text = selectedCharacter.name

        Picasso.get().load("${selectedCharacter.thumbnail.path}.jpg").into(binding.ivImg)

        binding.tvDescription.text = selectedCharacter.description
    }

    private fun setupObserver() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            when (it) {
                Results.MarvelApiComicsResults.Loading -> {
                    viewModel.isLoading.value = true
                }

                is Results.MarvelApiComicsResults.Failure -> {

                }

                is Results.MarvelApiComicsResults.Success -> {
                    comicsAdapter.updateItemsHome(it.comicsList.data.results)

                }
            }
        }
    }

    private fun setupAdapter() {
        comicsAdapter = AdapterComics()
        binding.rvComics.adapter = comicsAdapter


        binding.rvComics.apply {
            comicsAdapter.onItemClickListener = {
//                    val args = Bundle().apply {
//                        putSerializable(ARG, it)
//                    }
//                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, args)
            }
        }

    }
}