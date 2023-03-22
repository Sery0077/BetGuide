package dial.pro.betguide.ui.splash

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dial.pro.betguide.BuildConfig
import dial.pro.betguide.R
import dial.pro.betguide.databinding.FragmentSplashBinding
import dial.pro.betguide.ui.BackgroundHelper
import kotlinx.coroutines.*

class SplashFragment : Fragment() {

    private var binding: FragmentSplashBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        CoroutineScope(Dispatchers.IO).launch {
            BackgroundHelper.background_1 =
                getDrawable(context, "${BuildConfig.BASE_URL}img/background_1.png")
            BackgroundHelper.background_2 =
                getDrawable(context, "${BuildConfig.BASE_URL}img/background_2.png")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            withContext(Dispatchers.Main) {
                BackgroundHelper.background_1?.let {
                    requireActivity().window?.setBackgroundDrawable(it)
                } ?: {
                    requireActivity().window?.setBackgroundDrawableResource(R.drawable.background)
                }

                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun getDrawable(
        context: Context,
        imgUrl: String,
        requestListener: RequestListener<Drawable>? = null
    ): Drawable? {
        val result = Glide.with(context)
            .asDrawable()
            .load(imgUrl)
            .addListener(requestListener)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }
            })
            .submit()
            .get()

        return result
    }
}