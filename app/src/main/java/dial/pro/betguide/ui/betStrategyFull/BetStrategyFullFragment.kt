package dial.pro.betguide.ui.betStrategyFull

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dial.pro.betguide.R
import dial.pro.betguide.databinding.FragmentBetStrategyFullBinding


class BetStrategyFullFragment : Fragment() {

    private var strategyText: String = ""


    private var _binding: FragmentBetStrategyFullBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            strategyText = it.getString(getString(R.string.strategy_key), "Null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBetStrategyFullBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.twText.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(strategyText, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(strategyText)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}