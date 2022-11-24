package com.example.call_mapbox_api.homescreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.call_mapbox_api.R
import com.example.call_mapbox_api.databinding.FragmentSearchListBinding
import com.example.call_mapbox_api.homescreen.data.SearchRecycleAdapter
import com.example.call_mapbox_api.model.EvPointDetails
import com.example.call_mapbox_api.toConnections
import com.example.call_mapbox_api.util.ItemDataConverter
import kotlinx.coroutines.launch

class SearchListFragment : Fragment() {

    private val viewModel: SearchListViewModel by viewModels { SearchListViewModel.Factory }
    private var fragmentSearchlistBinding: FragmentSearchListBinding? = null
    //val progressBar = view?.findViewById<RecyclerView>(R.id.progressBar)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSearchListBinding.inflate(inflater, container, false)
        val view = binding.root
        fragmentSearchlistBinding = binding
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_search)
        var t = 0
        var f = true

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                t += dy
                if (((t > 0) || (t < 0)) and f) {
                    val inputMethodManager =
                        context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE)
                                as InputMethodManager
                    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                    f = false
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val adapter = viewModel.getAdapter()

                setFragmentResult(
                    "requestKey",
                    bundleOf("data" to viewModel.selectedPoint)
                )
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.detailFragment)


                if (recyclerView != null) {
                    recyclerView.layoutManager =
                        StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView.adapter = adapter
                }
            }

        }
        return view
    }
}

/*private fun showLoading(loading: Boolean) {
    when (loading) {
        true -> progressBar?.visibility = View.VISIBLE
        false -> progressBar?.visibility = View.GONE
    }
}*/