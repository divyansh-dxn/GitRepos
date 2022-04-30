package com.dxn.github.repos.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dxn.github.repos.databinding.DetailsFragmentBinding
import com.mukesh.MarkDown
import dagger.hilt.android.AndroidEntryPoint
import java.net.URL

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = args.repo
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.apply {
            detailsActionBar.apply {
                setupWithNavController(navController, appBarConfiguration)
                title = repo.name
            }
            markdown.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                runCatching {
                    setContent {
                        MaterialTheme {
                            MarkDown(
                                url = URL("https://raw.githubusercontent.com/${repo.owner.login}/${repo.name}/${repo.default_branch}/README.md"),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }.getOrElse {
                    Toast.makeText(
                        requireContext(),
                        it.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}