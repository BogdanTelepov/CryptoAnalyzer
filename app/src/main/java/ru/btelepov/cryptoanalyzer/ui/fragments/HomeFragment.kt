package ru.btelepov.cryptoanalyzer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import ru.btelepov.cryptoanalyzer.R
import ru.btelepov.cryptoanalyzer.adapters.CryptoCoinAdapter
import ru.btelepov.cryptoanalyzer.databinding.FragmentHomeBinding
import ru.btelepov.cryptoanalyzer.utils.NetworkResult
import ru.btelepov.cryptoanalyzer.viewModels.HomeFragmentViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val homeViewModel: HomeFragmentViewModel by viewModels()

    private val cryptoCoinAdapter: CryptoCoinAdapter by lazy { CryptoCoinAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchDataFromApi()

    }


    private fun fetchDataFromApi() {
        homeViewModel.fetchLastCryptoCurrency()
        homeViewModel.cryptoResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    cryptoCoinAdapter.differ.submitList(response.data?.data)
                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvListCoins.adapter = cryptoCoinAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}