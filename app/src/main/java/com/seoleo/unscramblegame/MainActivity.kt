package com.seoleo.unscramblegame

import android.os.Build
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
    private lateinit var uiState: GameUiState

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
            uiState = viewModel.next()
            updateUiState()
        }

        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            updateUiState()
        }

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputEditText.text.toString())
            updateUiState()
        }

        binding.inputEditText.addTextChangedListener { text: Editable? ->
            uiState = viewModel.handleUserInput(text = text.toString())
            updateUiState()
        }

        uiState = viewModel.init()
        updateUiState()
    }

    private fun updateUiState() {
        uiState.update(binding = binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVE_UI_STATE, uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        uiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(SAVE_UI_STATE, GameUiState::class.java)!!
        } else {
            savedInstanceState.getSerializable(SAVE_UI_STATE) as GameUiState
        }
    }

    companion object {
        private const val SAVE_UI_STATE = "save_ui_state"
    }
}