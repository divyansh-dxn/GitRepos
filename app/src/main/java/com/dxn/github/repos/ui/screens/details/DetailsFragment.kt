package com.dxn.github.repos.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dxn.github.repos.R
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.databinding.DetailsFragmentBinding
import com.mukesh.MarkDown
import dagger.hilt.android.AndroidEntryPoint
import java.net.URL

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    private val viewModel by hiltNavGraphViewModels<DetailsViewModel>(R.id.nav_graph)

    private lateinit var repo: Repo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        repo = args.repo
        viewModel.setRepo(repo)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.apply {
            detailsActionBar.apply {
                setupWithNavController(findNavController(), appBarConfiguration)
                title = repo.name
            }
            markdown.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MaterialTheme {
                        val readme by remember { viewModel.readme }
                        readme?.let {
                            MarkDown(
                                url = URL(it.download_url),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}