package dial.pro.betguide.ui.betTypes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dial.pro.betguide.databinding.FragmentBetTypesBinding

class BetTypesFragment : Fragment() {

    companion object {
        fun newInstance() = BetTypesFragment()
    }

    private val viewModel: BetTypesViewModel by viewModels()
    private lateinit var binding: FragmentBetTypesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBetTypesBinding.inflate(inflater)
        return binding.root
    }

}