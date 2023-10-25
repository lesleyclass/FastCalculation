package com.example.fastcalculation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fastcalculation.Extras.EXTRA_SETTINGS
import com.example.fastcalculation.databinding.FragmentResultBinding
import com.example.util.getSettings

class ResultFragment : Fragment() {
    private lateinit var fragmentResultBinding: FragmentResultBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        settings = it.getSettings(EXTRA_SETTINGS) ?: Settings()
        }
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)
        fragmentResultBinding.resultLabelTv.text= getString(R.string.points)
        fragmentResultBinding.resultValueTv.text= "${settings.points}"
        fragmentResultBinding.resetGameBt.setOnClickListener{
            (context as OnPlayGame).onPlayGame()
        }
        return fragmentResultBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(settings: Settings) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_SETTINGS, settings)

                }
            }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible=false
    }
}