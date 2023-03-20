package dial.pro.betguide.ui.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dial.pro.betguide.databinding.FragmentFaqBinding

class FaqFragment : Fragment() {

    companion object {
        fun newInstance() = FaqFragment()
    }

    private val viewModel: FaqViewModel by viewModels()
    private lateinit var binding: FragmentFaqBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaqBinding.inflate(inflater)
        return binding.root
    }

}