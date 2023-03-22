package dial.pro.betguide.ui.faq

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dial.pro.betguide.R
import dial.pro.betguide.databinding.FragmentHtmlTextBinding
import dial.pro.betguide.model.DataResult.Status.*
import kotlin.reflect.KFunction

class FaqFragment : Fragment() {

    companion object {
        fun newInstance() = FaqFragment()
    }

    private val viewModel: FaqViewModel by viewModels()

    private var _binding: FragmentHtmlTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHtmlTextBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeData() {
        viewModel.faqText.observe(viewLifecycleOwner) {
            when (it.status) {
                SUCCESS -> {
                    val text = it.data?.text

                    binding.twText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)

                    binding.progressBar.visibility = View.GONE
                    binding.twText.visibility = View.VISIBLE
                }
                ERROR -> {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.loading_error),
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(
                        getString(R.string.retry)
                    ) {
                        viewModel.getFAQ()
                    }.show()
                }
                LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.twText.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}