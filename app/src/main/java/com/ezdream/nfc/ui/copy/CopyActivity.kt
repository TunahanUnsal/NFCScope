package com.ezdream.nfc.ui.copy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.nfc.databinding.ActivityCopyBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CopyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCopyBinding
    private lateinit var viewModel: CopyActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: CopyActivity")

        binding = ActivityCopyBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[CopyActivityVM::class.java]
        setContentView(binding.root)

    }
}