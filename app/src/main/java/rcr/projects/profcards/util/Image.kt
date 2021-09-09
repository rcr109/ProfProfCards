package rcr.projects.profcards.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import rcr.projects.profcards.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class Image {

companion object{
    fun share(context: Context, cartao: View){
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val bitmap = getScreenShotFromView(cartao)
        bitmap?.let{
            saveMediaToStorage(context, bitmap)
        }
    }

    fun saveMediaToStorage(context: Context, bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            context.contentResolver?.also { resolver ->

                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                var imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                fos = imageUri?.let {
                    shareIntent(context, imageUri)
                    resolver.openOutputStream(it)
                }

                fos?.use {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                    Toast.makeText(context, "imagem capturada com sucesso", Toast.LENGTH_SHORT).show()
                }

            }
        }
        else {
            val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            shareIntent(context, Uri.fromFile(image))
            fos = FileOutputStream(image)
        }
    }

    private fun shareIntent(context: Context, imageUri: Uri) {
        var shareIntent : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/jpg"
        }
        context.startActivity(
            Intent.createChooser(
                shareIntent,
                context.resources.getText(R.string.txt_share)
            )
        )
    }

    fun getScreenShotFromView(cartao: View): Bitmap? {
        var screenShot: Bitmap? = null
        try {
            screenShot = Bitmap.createBitmap(
                cartao.measuredWidth,
                cartao.measuredHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(screenShot)
            cartao.draw(canvas)
        } catch (e: Exception){
            Log.e("PROFCARDS", "Falha ao capturar imagem" + e.message)
        }
        return screenShot
    }
}

}