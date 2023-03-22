package dial.pro.betguide.ui.betTypes

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

class BetTypesFragment : Fragment(), BetItemClickListener {

    private val adapter = BetStrategyRecyclerAdapter(this)

    private val viewModel: BetTypesViewModel by viewModels()

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        setupRecycler()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(key: String) {
        val item = viewModel.strategyMap.value?.data?.betTypes?.get(key) ?: throw NullPointerException("Key is null")
        val args = Bundle().apply {
            putString(getString(R.string.html_text_key), item)
        }

        findNavController().navigate(R.id.action_betTypesFragment_to_htmlTextFragment, args = args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        viewModel.strategyMap.observe(viewLifecycleOwner) {data ->
            when (data.status) {
                DataResult.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                    adapter.submitList(data.data?.betTypes?.map { it.key })
                }
                DataResult.Status.ERROR -> {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.loading_error),
                        Snackbar.LENGTH_INDEFINITE
                    )
                        .setAction(getString(R.string.retry)) {
                            viewModel.getTypes()
                        }
                        .show()
                }
                DataResult.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recycler.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.recycler.adapter = adapter
    }
}