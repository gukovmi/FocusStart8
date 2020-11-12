package com.example.focusstart8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.focusstart8.data.ImgurApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class MainActivity : AppCompatActivity() {
    private val cameraRequest: Int = 0
    private var disposable: Disposable? = null
    private lateinit var outputPhotoUri: Uri
    private lateinit var photoFile: File
    private val imgurApiService by lazy {
        ImgurApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePhotoButton.setOnClickListener {
            savePhoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK) {
            photoImageView.setImageURI(outputPhotoUri)

            disposable = imgurApiService.postImage(
                titleEditText.text.toString(),
                descriptionEditText.text.toString(),
                "file",
                getPartFile()
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this, "Uploaded on ${it.data.link}", Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun savePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        photoFile = File.createTempFile("test", ".jpg", Environment.getExternalStorageDirectory())
        outputPhotoUri = FileProvider.getUriForFile(
            this@MainActivity,
            "com.example.focusstart8.provider",
            photoFile
        )
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputPhotoUri)
        startActivityForResult(cameraIntent, cameraRequest)
    }

    private fun getPartFile(): MultipartBody.Part = MultipartBody.Part.createFormData(
        "image",
        photoFile.name,
        RequestBody.create("multipart/form-data".toMediaTypeOrNull(), photoFile)
    )

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}