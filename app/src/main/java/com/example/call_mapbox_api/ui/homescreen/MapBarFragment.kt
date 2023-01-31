package com.example.call_mapbox_api.ui.homescreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.call_mapbox_api.R
import com.example.call_mapbox_api.databinding.FragmentMapbarBinding
import com.example.call_mapbox_api.util.hideKeyboard
import com.example.call_mapbox_api.util.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapBarFragment : Fragment() {

    private var _binding: FragmentMapbarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapbarBinding.inflate(inflater, container, false)
        val view = binding.root
        val searchBar = binding.searchbarFragment.inputSearch
        searchBar.focusable = View.NOT_FOCUSABLE
        searchBar.setOnClickListener {
            context?.let {
                showKeyboard(view, it)
            }
            //searchBar.focusable = View.FOCUSABLE
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.searchlistFragment)
        }
        return view
    }
}