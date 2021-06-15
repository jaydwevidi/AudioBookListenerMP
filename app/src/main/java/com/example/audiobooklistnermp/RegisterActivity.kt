package com.example.audiobooklistnermp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import com.example.audiobooklistnermp.objects.UserDetails
import com.example.audiobooklistnermp.databinding.ActivityRegisterBinding
import com.example.audiobooklistnermp.objects.PlaylistObject
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)
        binding.progressBarRegister.visibility = View.INVISIBLE

        val uid = intent.getStringExtra("uid")
        binding.TVTandC.movementMethod = LinkMovementMethod.getInstance()
        binding.buttonRegister.isEnabled = false

        val listOfTempPlaylist = mutableListOf<PlaylistObject>()
        listOfTempPlaylist.add(
            PlaylistObject(
                    videoIDs = listOf<String>(
                        "dsaf",
                        "asfda",
                        "adsgsd",
                        "asdfasd",
                        "sdfas"
                    )
            )
        )

        binding.buttonRegister.setOnClickListener(View.OnClickListener {
            val userObject = UserDetails(
                binding.ETName.text.toString(),
                binding.ETEMail.text.toString(),
                listOfTempPlaylist
            )

            binding.progressBarRegister.visibility = View.VISIBLE

            Firebase.database.reference
                .child("Users")
                .child(uid!!)
                .setValue(userObject)

            binding.progressBarRegister.visibility = View.INVISIBLE

            openTasksActivity()
        })

        binding.switchAgree.setOnCheckedChangeListener { dbuttonView, isChecked ->
            binding.buttonRegister.isEnabled = isChecked
        }
    }

    private fun openTasksActivity() {
        val intentToOpenTasks = Intent(this, Categories::class.java)
        startActivity(intentToOpenTasks)
        finish()
    }
}
