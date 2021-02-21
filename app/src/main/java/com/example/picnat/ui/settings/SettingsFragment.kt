package com.example.picnat.ui.settings



import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.PicnatApplication
import com.example.picnat.R
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.example.picnat.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject
class SettingsFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
    }
    private var uri: Uri? = null
    lateinit var mStorageRef: StorageReference

    companion object{
        const val TAKE_PIC_REQUEST_CODE = 0
        const val CHOOSE_PIC_REQUEST_CODE = 1
        const val MEDIA_TYPE_IMAGE = 2
        const val CREATE_REQUEST_CODE = 40
        const val OPEN_REQUEST_CODE = 41
        const val PERMISSION_CODE = 1001
        const val SAVE_REQUEST_CODE = 42
        const val TAG = "settingsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mStorageRef = FirebaseStorage.getInstance().getReference("Images")
        logout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }

        myProfile.setOnClickListener {
            pickImageFromGallery(it)
        }
    }

    private fun requestStoragePermissions(){
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_CODE
        )
    }

    private fun pickImageFromGallery(v: View){
        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Upload or Take a photo")
        builder.setPositiveButton("Upload"){ _, _ ->

            val choosePictureIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            choosePictureIntent.addCategory(Intent.CATEGORY_OPENABLE)
            choosePictureIntent.type="image/*"
            startActivityForResult(choosePictureIntent, OPEN_REQUEST_CODE)
        }
        builder.setNegativeButton("Take Photo"){ _, _ ->
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{pictureIntent ->
                pictureIntent.resolveActivity(activity?.packageManager!!)?.also{
                    //TODO with bitmap
                    startActivityForResult(pictureIntent, OPEN_REQUEST_CODE)
                }
            }
//            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            takePicture.addCategory(Intent.CATEGORY_OPENABLE)
//            takePicture.type="image/*"
//            startActivityForResult(takePicture, OPEN_REQUEST_CODE)
        }
        val dialog = builder.create()
        dialog.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG,"${requestCode}|| $resultCode || ${data?.data}")
        if(requestCode==41 && resultCode == RESULT_OK && data != null && data.data!=null){
            val storageRef = FirebaseStorage.getInstance()
                .reference
                .child("images/${FirebaseAuth.getInstance().currentUser?.uid}")
            storageRef.putFile(data.data!!)
                .addOnSuccessListener { takeSnapshot->
                    val url = takeSnapshot.metadata?.reference?.downloadUrl
                    myProfile.setImageURI(data.data)
                }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as PicnatApplication).appComponent.inject(this)
    }

//    private fun uploadImageAndSaveUri(bitmap: Bitmap){
//        Log.d(TAG,"Halo")
//        val stream = ByteArrayOutputStream()
//        val storageRef = FirebaseStorage.getInstance()
//            .reference
//            .child("images/${FirebaseAuth.getInstance().currentUser?.uid}")
//
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
//        val image = stream.toByteArray()
//
//        val upload = storageRef.putBytes(image)
//        upload.addOnCompleteListener{uploadTask ->
//            if(uploadTask.isSuccessful){
//                storageRef.downloadUrl.addOnCompleteListener {urlTask->
//                    urlTask.result?.let {
//                        uri = it
//                        Log.d(TAG,uri.toString())
//
//                        myProfile.setImageBitmap(bitmap)
//                    }
//
//                }
//            }else{
//                uploadTask.exception?.let {
//                    Log.d(TAG,it.message!!)
//                }
//            }
//        }
//    }
//
//    private fun readFileContent(currentUri: Uri){
//        val pfd = context?.contentResolver?.openFileDescriptor(currentUri,"r")
//        val fd = pfd?.fileDescriptor
//        val bmp = BitmapFactory.decodeFileDescriptor(fd)
//        pfd?.close()
//        myProfile.setImageBitmap(bmp)
//    }

}
