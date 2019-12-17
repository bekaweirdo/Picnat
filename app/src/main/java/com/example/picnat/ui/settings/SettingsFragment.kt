package com.example.picnat.ui.settings



import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.PicnatApplication
import com.example.picnat.R
import com.example.picnat.ui.auth.AuthViewModel
import com.example.picnat.ui.auth.AuthViewModelFactory
import com.example.picnat.ui.login.LoginActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_settings.*
import java.io.File
import javax.inject.Inject
class SettingsFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory

    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[AuthViewModel::class.java]
    }
    private var url: Uri? = null
    lateinit var mStorageRef: StorageReference

    companion object{
        const val TAKE_PIC_REQUEST_CODE = 0
        const val CHOOSE_PIC_REQUEST_CODE = 1
        const val MEDIA_TYPE_IMAGE = 2
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
            Toast.makeText(activity,"Change Pic Pressed", Toast.LENGTH_SHORT).show()

            //show dialog
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Upload or Take a photo")
            builder.setPositiveButton("Upload"){dialog,_ ->
                val choosePictureIntent = Intent(Intent.ACTION_GET_CONTENT)
                choosePictureIntent.type = "image/*"
                startActivityForResult(choosePictureIntent, CHOOSE_PIC_REQUEST_CODE)
            }
            builder.setNegativeButton("Take Photo"){dialog, _ ->
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                //  val Ref = mStorageRef.child(takePicture)
                //startActivityForResult(takePicture, TAKE_PIC_REQUEST_CODE)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun PhotoUploaed(){
        // val Ref = mStorageRef.child()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1 && resultCode == RESULT_OK && data != null && data.data!=null){
            url = data.data
            Glide.with(this)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .into(myProfile)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as PicnatApplication).appComponent.inject(this)
    }
}
