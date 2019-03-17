package jellyfish026.trunkhackathon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class GridAdapter internal constructor(
    context: Context,
    private val layoutId: Int,
    iList: List<Int>,
    private val names: Array<String>
) : BaseAdapter() {

    private var imageList = ArrayList<Int>()
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    internal inner class ViewHolder {
        var imageView: ImageView? = null
        var textView: TextView? = null
    }

    init {
        imageList = iList as ArrayList<Int>
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false)
            holder = ViewHolder()

            holder.imageView = convertView!!.findViewById(R.id.image_view)
            holder.textView = convertView.findViewById(R.id.text_view)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.imageView!!.setImageResource(imageList[position])
        holder.textView!!.text = names[position]

        return convertView
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}