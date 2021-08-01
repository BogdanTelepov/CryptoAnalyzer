package ru.btelepov.cryptoanalyzer.ui.fragments


import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch
import ru.btelepov.cryptoanalyzer.R
import ru.btelepov.cryptoanalyzer.ui.adapters.CryptoCoinAdapter
import ru.btelepov.cryptoanalyzer.databinding.FragmentHomeBinding
import ru.btelepov.cryptoanalyzer.data.network.NetworkResult
import ru.btelepov.cryptoanalyzer.extensions.observeOnce
import ru.btelepov.cryptoanalyzer.data.network.ConnectionListener
import ru.btelepov.cryptoanalyzer.ui.viewModels.HomeFragmentViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(), SearchView.OnQueryTextListener,
    ConnectionListener.ConnectivityReceiverListener {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val homeViewModel: HomeFragmentViewModel by viewModels()

    private val cryptoCoinAdapter: CryptoCoinAdapter by lazy { CryptoCoinAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        ConnectionListener.connectivityReceiverListener = this
        requireActivity().registerReceiver(
            ConnectionListener(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        fetchDataFromApi()



//        lifecycleScope.launchWhenStarted {
//            readFromDatabase()
//        }

        refreshData()


    }

    private fun refreshData() {
        binding.swipeLayout.setOnRefreshListener {
            Log.d("HOME_FRAGMENT", "Api call")
            homeViewModel.fetchLastCryptoCurrency()
        }
    }


    private fun readFromDatabase() {
        lifecycleScope.launch {

            homeViewModel.readAllCoins.observeOnce(viewLifecycleOwner) { data ->
                if (data.isNotEmpty()) {
                    Log.d("HOME_FRAGMENT", "read Database Call!")
                    cryptoCoinAdapter.setData(data)
                    binding.progressBar.visibility = View.GONE
                } else {
                    fetchDataFromApi()

                }
            }
        }
    }


    private fun fetchDataFromApi() {
        Log.d("HOME_FRAGMENT", "API Call!")

        homeViewModel.fetchLastCryptoCurrency()

        homeViewModel.cryptoCoinResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    binding.swipeLayout.isRefreshing = false
                    response.data?.let { cryptoCoinAdapter.setData(it.data.toList()) }
                }

                is NetworkResult.Error -> {

                    binding.swipeLayout.isRefreshing = false
                    loadCache()
                    Log.d("HOME_FRAGMENT", "loadCache Call!")
                    Snackbar.make(binding.root, response.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
                is NetworkResult.Loading -> {
                    binding.swipeLayout.isRefreshing = true
                }
            }
        }
    }

    private fun loadCache() {

        lifecycleScope.launch {

            homeViewModel.readAllCoins.observe(viewLifecycleOwner) { data ->
                if (data != null) {
                    cryptoCoinAdapter.setData(data)
                }
            }
        }
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            Snackbar.make(binding.root, "No Internet Connection", Snackbar.LENGTH_LONG).show()
        } else {

            Snackbar.make(binding.root, "You are Online", Snackbar.LENGTH_LONG).show()

        }
    }


    private fun setupRecyclerView() {
        binding.rvListCoins.adapter = cryptoCoinAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_coin_menu, menu)
        val search = menu.findItem(R.id.menu_search_coin)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchInDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchInDatabase(newText)
        }
        return true
    }

    private fun searchInDatabase(query: String) {
        val searchQuery = "%$query%"
        homeViewModel.searchFromDatabase(searchQuery).observe(viewLifecycleOwner) {
            cryptoCoinAdapter.setData(it)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}