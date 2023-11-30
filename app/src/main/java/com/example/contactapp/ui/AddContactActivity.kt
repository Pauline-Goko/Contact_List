package com.example.contactapp.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.health.connect.datatypes.ExerciseRoute.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityAddContactBinding
import com.example.contactapp.model.ContactData
import com.example.contactapp.viewmodel.ContactsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.io.File

class AddContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddContactBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    lateinit var photoFile: File
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var myLocation: Location

    
    val cameraLauncher=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val photo = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.ivAvatar.setImageBitmap(photo)
        }
    }

    val locationRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()){ result ->
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            getLocationUpdates()

        }
        else{
            Toast.makeText(this, "Please grant location permission", Toast.LENGTH_LONG).show()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onResume() {

        super.onResume()
        binding.btnSave.setOnClickListener {
            checkError()
            validateContacts()
        }
        binding.ivAvatar.setOnClickListener {
            capturePhoto()
        }
        getLocationUpdates()
    }

    fun getLocationUpdates(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            val locationRequest = LocationRequest.Builder(10000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            val locationCallBack = object : LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations){
                        Toast.makeText(baseContext,
                                "Lat: ${location.latitude}, Long: ${location.longitude}",
                                Toast.LENGTH_LONG).show()
                    }

                }
            }

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallBack, Looper.getMainLooper())

        }
        else{
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun capturePhoto(){
        var photoIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile=getPhotoFile("photo_${System.currentTimeMillis()}")
        val fileUri = FileProvider.getUriForFile(this,"com.example.contactapp.provider",photoFile)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)

        cameraLauncher.launch(photoIntent)

    }

    private  fun getPhotoFile(fileName: String): File{
        val folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var tempFile = File.createTempFile(fileName, ".jpeg", folder)
        return  tempFile
    }


    fun checkError(){

        binding.tieFirstName.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilFirstName.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )

        binding.tiePhoneNumber.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilPhoneNumber.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )
        binding.tieEmail.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilEmail.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        } )
    }
    fun validateContacts(){
        val name =binding. tieFirstName.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val email =binding. tieEmail.text.toString()

        var error = false

        if (name.isBlank()){
            binding.tieFirstName.error = getString(R.string.name)
            error = true
        }

        if (phoneNumber.isBlank()){
            binding.tiePhoneNumber.error = getString(R.string.phoneNumber)
            error = true
        }

        if (email.isBlank()){
            binding.tieEmail.error = getString(R.string.email)
            error = true
        }


        if (!error){
            val newContact = ContactData(contactId = 0, contactName = name, phoneNumber = phoneNumber, emailAddress = email, image = "")
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this, "Contact saved", Toast.LENGTH_LONG).show()
                finish()
        }

    }

}


