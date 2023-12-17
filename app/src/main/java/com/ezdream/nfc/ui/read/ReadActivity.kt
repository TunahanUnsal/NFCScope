package com.ezdream.nfc.ui.read

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ezdream.nfc.databinding.ActivityReadBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var viewModel: ReadActivityVM
    private var nfcAdapter: NfcAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("TAG", "onCreate: ReadActivity")

        binding = ActivityReadBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ReadActivityVM::class.java]
        setContentView(binding.root)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            // Cihaz NFC özelliğini desteklemiyor
            Toast.makeText(this, "Cihazınız NFC özelliğini desteklemiyor", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        if (!nfcAdapter!!.isEnabled) {
            // NFC etkin değil, kullanıcıyı ayarlara yönlendir
            Toast.makeText(this, "Lütfen NFC özelliğini etkinleştirin", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_NFC_SETTINGS))
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        val filters = arrayOf(intent)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE
        )
        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, filters, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            val data = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_DATA)
            val sec = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_SECURE_ELEMENT_NAME)
            val id = tag?.id // Etiket ID'sini almak için
            val techList = tag?.techList // Etiketin desteklediği teknolojileri almak için
            binding.text.text = id.contentToString() + "\n" + techList.contentToString()  + "\n" + sec.toString()
        }
    }


}