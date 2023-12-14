package com.mvn1101.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mvn1101.composition.R
import com.mvn1101.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    val percent = if (gameResult.countOfRightAnswers == 0) {
        0
    } else {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        percent
    )
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileId(winner))
}
private fun getSmileId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}