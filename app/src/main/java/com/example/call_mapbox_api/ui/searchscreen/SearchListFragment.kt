package com.example.call_mapbox_api.ui.searchscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.call_mapbox_api.R
import com.example.call_mapbox_api.data.remote.EvPointsEntity
import com.example.call_mapbox_api.databinding.FragmentSearchListBinding
import com.example.call_mapbox_api.util.hideKeyboard
import com.example.call_mapbox_api.util.itemDataConverter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private val viewModel: SearchListViewModel by activityViewModels()

    private var fragmentSearchListBinding: FragmentSearchListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSearchListBinding.inflate(inflater, container, false)
        val view = binding.root
        fragmentSearchListBinding = binding
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_search)
        var t = 0
        var f = true

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                t += dy
                if (((t > 0) || (t < 0)) and f) {
                    context?.let { hideKeyboard(view, it) }
                    f = false
                }
            }
        })

        viewLifecycleOwner
            .lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.listOfItems.observe(viewLifecycleOwner) {
                        val adapter = SearchRecycleAdapter(
                            it, object : SearchRecycleAdapter.OnAdapterListener {
                                override fun onClick(address: EvPointsEntity) {
                                    viewModel.setDetailItems(itemDataConverter(address))
                                    val action =
                                        SearchListFragmentDirections
                                            .actionSearchlistFragmentToDetailFragment()
                                    view.findNavController().navigate(action)
                                }
                            })
                        recyclerView.layoutManager =
                            StaggeredGridLayoutManager(
                                1,
                                StaggeredGridLayoutManager.VERTICAL
                            )
                        recyclerView.adapter = adapter
                    }
                }
            }
        return view
    }
}
