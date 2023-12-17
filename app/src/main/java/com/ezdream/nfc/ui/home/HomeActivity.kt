package com.ezdream.nfc.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.nfc.databinding.ActivityHomeBinding
import com.ezdream.nfc.ui.read.ReadActivity
import com.ezdream.nfc.util.DeviceUtil.getSoftwareVersion
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: LoginActivity")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[HomeActivityVM::class.java]
        setContentView(binding.root)

        binding.versionText.text = getSoftwareVersion(this)

        binding.copyButton.setOnClickListener {

        }

        binding.readButton.setOnClickListener {
            val intent = Intent(baseContext, ReadActivity::class.java)
            startActivity(intent)
        }


    }
}