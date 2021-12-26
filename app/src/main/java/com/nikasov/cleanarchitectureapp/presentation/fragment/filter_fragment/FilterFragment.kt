package com.nikasov.cleanarchitectureapp.presentation.fragment.filter_fragment

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikasov.cleanarchitectureapp.common.extensions.collectWhenStarted
import com.nikasov.cleanarchitectureapp.databinding.FragmentFilterBinding
import com.nikasov.cleanarchitectureapp.domain.model.GameDetailsInfo
import com.nikasov.cleanarchitectureapp.presentation.adapter.decoration.VerticalSpaceDecoration
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.FilterType
import com.nikasov.cleanarchitectureapp.presentation.adapter.game_details.GameDetailsInfoAdapter
import com.nikasov.cleanarchitectureapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FilterFragment: BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate) {

    private val filterViewModel: FilterViewModel by viewModels()

    @Inject
    lateinit var gameDetailsInfoAdapter: GameDetailsInfoAdapter

    override fun setupViewModelCallbacks() {
        filterViewModel.filterList.collectWhenStarted(this) {
            it.getResult(
                loading = {
                    loadingState(true)
                },
                successes = { list ->
                    loadingState(false)
                    setupFilters(list)
                },
                error = {
                    loadingState(false)
                }
            )
        }
    }

    override fun setupViews() {
        with(requireBinding().infoRecycler) {
            adapter = gameDetailsInfoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalSpaceDecoration())

            gameDetailsInfoAdapter.filterType = FilterType.Selection
        }
    }

    private fun setupFilters(list: MutableList<GameDetailsInfo>?) {
        Timber.d(list?.joinToString { it.content.joinToString { it.name } })
        gameDetailsInfoAdapter.submitList(list)
    }

}