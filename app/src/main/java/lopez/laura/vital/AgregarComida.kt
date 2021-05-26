package lopez.laura.vital

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storageMetadata
import kotlinx.android.synthetic.main.activity_agregar_comida.*
import kotlinx.android.synthetic.main.activity_agregar_comida.iv_comida
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.vista_comida.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class AgregarComida : AppCompatActivity() {

    var photouri: Uri?=null
    private val CAMERA_PERSMISSION_CODE  = 1
    private val CAMERA_CODE = 2
    private lateinit var storage : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_comida)

        storage = FirebaseFirestore.getInstance()


        tomar_foto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_CODE)
            } else {
                ActivityCompat.requestPermissions(
                        this as Activity,
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERSMISSION_CODE
                )
            }
        }

        btn_continuar.setOnClickListener {
            if (!calorias.text.isNullOrEmpty() && photouri != null){
                photouri?.let { it1 -> uploadImgToFirebase(it1) }
            } else {
                Toast.makeText(
                        baseContext,
                        "Debes llenar todos los campos.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERSMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_CODE)
            } else {
                Toast.makeText(
                        baseContext,
                        "El permiso no ha sido aceptado.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_CODE){
                val image: Bitmap = data!!.extras!!.get("data") as Bitmap

                iv_comida.setImageBitmap(image)
                saveMediaToStorage(image)
            }
        }
    }

    fun saveMediaToStorage(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"

        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            baseContext?.contentResolver?.also { resolver ->

                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                fos = imageUri?.let { resolver.openOutputStream(it) }

                photouri = imageUri
            }
        } else {
            val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }

    }

    fun uploadImgToFirebase(fileUri: Uri){
        val fileName = UUID.randomUUID().toString() +".jpg"
        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")
        val valCalorias = calorias.text

        val metadata = storageMetadata {
            contentType = "image/jpg"
            setCustomMetadata("calorias", "$valCalorias")
        }


        refStorage.putFile(fileUri, metadata)
                .addOnSuccessListener(
                        OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                                Toast.makeText(baseContext!!, "Guardada en Storage", Toast.LENGTH_SHORT).show()
                                uploadImgNameToFirebase(fileName)
                            }
                            //Glide.with(this).load(refStorage.child("images/$fileName").downloadUrl).into(iv_comida)
                        })
                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
    }

    fun uploadImgNameToFirebase(nombre: String){

        val nombreGuardar = hashMapOf(
            "nombre" to nombre
            )

        storage.collection("imagesnames")
            .add(nombreGuardar)
            .addOnSuccessListener {
                Toast.makeText(this, "Se agrego el nombre a BD", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "No se agrego el nombre", Toast.LENGTH_SHORT).show()
            }

    }

}