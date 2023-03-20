package dial.pro.betguide.ui.betStrategy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dial.pro.betguide.databinding.FragmentBetStrategyBinding

class BetStrategyFragment : Fragment() {

    companion object {
        fun newInstance() = BetStrategyFragment()
    }

    private val viewModel: BetStrategyViewModel by viewModels()
    private lateinit var binding: FragmentBetStrategyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBetStrategyBinding.inflate(inflater)
        return binding.root
    }


}