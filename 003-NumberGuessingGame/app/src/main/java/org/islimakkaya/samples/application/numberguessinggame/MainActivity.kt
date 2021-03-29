package org.islimakkaya.samples.application.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.islimakkaya.samples.application.numberguessinggame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var randomNum = 0
    private var trial = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        randomNum = Random.nextInt(1, 101)

        setScoreText(trial)

        binding.buttonControl.setOnClickListener {
            if(trial == 0) {
                setFailedMessage()
                disableInput()
            }
            else {
                onButtonControlClicked()
            }
        }
    }

    private fun onButtonControlClicked() {
        val num = guessNum()
        if(num.isEmpty()) {
            binding.textInputEditNumber.error = getString(R.string.input_error_message)
            return
        }
        else
            playRandomNumGame(num.toInt())
    }

    private fun setFailedMessage()
    {
        binding.textMessage.text = getString(R.string.fail_message)
        binding.textScore.text = null
    }

    private fun setScoreText(trial: Int)
    {
        binding.textScore.text = getString(R.string.score_message, trial)
    }

    private fun disableInput()
    {
        binding.textInputEditNumber.isEnabled = false
        binding.buttonControl.isEnabled = false
    }

    private fun guessNum() : String
    {
        return binding.textInputEditNumber.text.toString()
    }

    private fun playRandomNumGame(num: Int)
    {
        when {
            num == randomNum -> {
                binding.textMessage.text = getString(R.string.win_message, trial)
                binding.textScore.text = null
                disableInput()
            }
            num < randomNum -> {
                trial--
                binding.textMessage.text = getString(R.string.biggernum_message)
                setScoreText(trial)
                binding.textInputEditNumber.setText("")
            }
            else -> {
                trial--
                binding.textMessage.text = getString(R.string.smallernum_message)
                setScoreText(trial)
                binding.textInputEditNumber.setText("")
            }
        }
    }
}