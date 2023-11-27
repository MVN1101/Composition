package com.mvn1101.composition.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mvn1101.composition.R
import com.mvn1101.composition.databinding.FragmentChooseLevelBinding
import com.mvn1101.composition.databinding.FragmentGameBinding
import com.mvn1101.composition.domain.entity.Level

class ChooseLevelFragment: Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
//            Log.d("Level", "TEST")
            }
            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
//            Log.d("Level", "EASY")
            }
            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
//            Log.d("Level", "NORMAL")
            }
            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
//            Log.d("Level", "HARD")
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}