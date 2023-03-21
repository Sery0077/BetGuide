package dial.pro.betguide.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dial.pro.betguide.R
import dial.pro.betguide.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupNavigation() {
        binding.run {
            btnBetStrategy.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_betStrategyFragment)
            }

            btnBetTypes.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_betTypesFragment)
            }

            btnFaq.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_faqFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}