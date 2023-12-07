package com.mvn1101.composition.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mvn1101.composition.R
import com.mvn1101.composition.databinding.FragmentGameBinding
import com.mvn1101.composition.domain.entity.GameResult
import com.mvn1101.composition.domain.entity.GameSettings
import com.mvn1101.composition.domain.entity.Level

class GameFragment : Fragment() {

    private lateinit var level: Level
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    //    private val gameSettingsTest: GameSettings = GameSettings(
//        10,
//        3,
//        50,
//        8
//    )
//    private val gameResultTest: GameResult = GameResult(
//        true,
//        10,
//        3,
//        gameSettingsTest
//    )
    private lateinit var number: String
    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startGame(level)
        viewModel.question.observe(viewLifecycleOwner) {
            with(binding) {
                tvSum.text = it.sum.toString()
                tvLeftNumber.text = it.visibleNumber.toString()
                tvOption1.text = it.options[0].toString()
                tvOption2.text = it.options[1].toString()
                tvOption3.text = it.options[2].toString()
                tvOption4.text = it.options[3].toString()
                tvOption5.text = it.options[4].toString()
                tvOption6.text = it.options[5].toString()

                tvOption1.setOnClickListener(View.OnClickListener {
                    number = tvOption1.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                })

                tvOption2.setOnClickListener(View.OnClickListener {
                    number = tvOption2.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                })

                tvOption3.setOnClickListener(View.OnClickListener {
                    number = tvOption3.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                })

                tvOption4.setOnClickListener(View.OnClickListener {
                    number = tvOption4.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                })

                tvOption5.setOnClickListener(View.OnClickListener {
                    number = tvOption5.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                })

                tvOption6.setOnClickListener(View.OnClickListener {
                    number = tvOption6.text.toString()
                    viewModel.chooseAnswer(number.toInt())
                    launchGameFinishedFragment(gameResult)
                })
            }


        }

        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it.toString()

        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            gameResult = it
            launchGameFinishedFragment(gameResult)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner){
        }



    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    companion object {

        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}