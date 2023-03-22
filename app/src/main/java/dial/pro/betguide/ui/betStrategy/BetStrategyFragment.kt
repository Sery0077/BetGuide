package dial.pro.betguide.ui.betStrategy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dial.pro.betguide.R
import dial.pro.betguide.databinding.FragmentRecyclerBinding
import dial.pro.betguide.model.DataResult
import dial.pro.betguide.ui.adapter.BetItemClickListener
import dial.pro.betguide.ui.adapter.BetStrategyRecyclerAdapter

class BetStrategyFragment : Fragment(), BetItemClickListener {

    companion object {
        fun newInstance() = BetStrategyFragment()
    }

    private val viewModel: BetStrategyViewModel by viewModels()

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    private val adapter = BetStrategyRecyclerAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecycler()

        viewModel.strategyMap.observe(viewLifecycleOwner) { data ->
            when (data.status) {
                DataResult.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                    adapter.submitList(data.data?.strategy?.map { it.key })
                }
                DataResult.Status.ERROR -> {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.loading_error),
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(getString(R.string.retry)) {
                            viewModel.getStrategy()
                        }
                        .show()
                }
                DataResult.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recycler.visibility = View.GONE
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupRecycler() {
        binding.recycler.adapter = adapter
    }

    override fun onItemClick(key: String) {
        val strategyText = viewModel.strategyMap.value?.data?.strategy?.get(key) ?: throw NullPointerException("Key is null")

        val args = Bundle().apply {
            putString(getString(R.string.html_text_key), strategyText)
        }

        findNavController().navigate(R.id.action_betStrategyFragment_to_htmlTextFragment, args = args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}