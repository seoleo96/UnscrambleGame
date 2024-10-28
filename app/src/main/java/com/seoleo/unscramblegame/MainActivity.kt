package com.seoleo.unscramblegame

import android.os.Bundle
import android.text.Editable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.seoleo.unscramblegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = GameViewModel(GameRepositoryImpl(ShuffleStrategyImpl()))

        binding.nextButton.setOnClickListener {
            val uiState: GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }

        binding.skipButton.setOnClickListener {
            val uiState: GameUiState = viewModel.skip()
            uiState.update(binding = binding)
        }

        binding.checkButton.setOnClickListener {
            val uiState: GameUiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.inputEditText.addTextChangedListener { text: Editable? ->
            val uiState: GameUiState = viewModel.handleUserInput(text = text.toString())
            uiState.update(binding = binding)
        }

        val uiState = viewModel.init()
        uiState.update(binding = binding)
    }
}