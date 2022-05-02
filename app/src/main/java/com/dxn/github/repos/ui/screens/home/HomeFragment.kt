package com.dxn.github.repos.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxn.github.repos.R
import com.dxn.github.repos.common.util.Organization
import com.dxn.github.repos.common.util.RepoSort
import com.dxn.github.repos.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.nav_graph)

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RepoListAdapter { repo ->
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(repo)
            findNavController().navigate(directions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.apply {
            homeActionBar.apply {
                setupWithNavController(navController, appBarConfiguration)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.settings -> {
                            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
                            true
                        }
                        else -> false
                    }
                }
            }
            repoItemsRv.layoutManager = LinearLayoutManager(requireContext())
            repoItemsRv.adapter = adapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getData(Organization.JETBRAINS, RepoSort.UPDATED).distinctUntilChanged()
                .collectLatest { adapter.submitData(it) }
        }
    }
}