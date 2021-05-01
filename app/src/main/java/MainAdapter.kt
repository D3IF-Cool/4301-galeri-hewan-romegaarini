import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0031.galerihewan.R
import org.d3if0031.galerihewan.databinding.ListItemBinding
import org.d3if0031.galerihewan.model.Hewan
import org.d3if0031.galerihewan.network.HewanApi

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val data = mutableListOf<Hewan>()
    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hewan: Hewan) {
            with(binding) {
                namaTextView.text = hewan.nama
                latinTextView.text = hewan.namaLatin
                Glide.with(imageView.context)
                    .load(HewanApi.getHewanUrl(hewan.imageId))
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }


    override fun getItemCount(): Int {
        return data.size
    }
}
