import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.lab_week_05_sandy.ImageLoader
import com.example.lab_week_05_sandy.R

class GlideLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context).load(imageUrl).placeholder(R.drawable.placeholder_img).error(R.drawable.placeholder_img).centerCrop().into(imageView)
    }
}