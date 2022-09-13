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
import com.example.projeto.projectmarvel.data.storage.SharedPreferences
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

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        setupView()
        setupObserver()
        setupAdapter()
    }

    private fun setupView() {
        viewModel.setSearch(selectedCharacter.id.toString())

        binding.tvTitle.text = selectedCharacter.name

        Picasso.get().load("${selectedCharacter.thumbnail.path}.jpg").into(binding.ivImg)

        animate()

        if (!selectedCharacter.description.isNullOrEmpty()) {
            binding.tvDescription.text = selectedCharacter.description
        } else {
            binding.tvDescription.setText(R.string.no_description)
        }

        binding.tvAddCharacter.setOnClickListener {
            SharedPreferences(requireContext()).compare(
                selectedCharacter.id,
                selectedCharacter.name,
                selectedCharacter.thumbnail
            )
        }

    }

    private fun animate() {
        binding.ivImg.animate().apply {
            duration = 1000
            scaleXBy(.5f)
            scaleYBy(.5f)
            rotationYBy(360f)
            translationYBy(200f)
        }.withEndAction {
            binding.ivImg.animate().apply {
                duration = 1000
                scaleXBy(-.5f)
                scaleYBy(-.5f)
                rotationXBy(360f)
                translationYBy(-200f)
            }
        }.start()
    }

    private fun setupObserver() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            when (it) {
                Results.MarvelApiComicsResults.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    viewModel.isLoading.value = true
                }

                is Results.MarvelApiComicsResults.Failure -> {

                }

                is Results.MarvelApiComicsResults.Success -> {
                    binding.progress.visibility = View.GONE
                    if (it.comicsList.data.results.isNullOrEmpty()) {
                        binding.rvComics.visibility = View.GONE
                        binding.tvNoComics.visibility = View.VISIBLE
                    } else {
                        binding.rvComics.visibility = View.VISIBLE
                        binding.tvNoComics.visibility = View.GONE
                        comicsAdapter.updateItemsHome(it.comicsList.data.results)
                    }
                    //comicsAdapter.updateItemsHome(it.comicsList.data.results)
                }
            }
        }
    }

    private fun setupAdapter() {
        comicsAdapter = AdapterComics()
        binding.rvComics.adapter = comicsAdapter


        binding.rvComics.apply {
            comicsAdapter.onItemClickListener = {

            }
        }

    }
}