package jellyfish026.trunkhackathon

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.content.Intent





class TakoyakiFragment : Fragment(), AdapterView.OnItemClickListener {

    var imageList = arrayListOf<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_takoyaki, container, false)

        for (i in Data.takoyakikun) {
            imageList.add(R.drawable.takoyaki_fight)
        }

        val gridview = view.findViewById<GridView>(R.id.gridview)

        val Adapter = GridAdapter(
            this.context!!,
            R.layout.grid_item,
            imageList,
            Data.takoyakikun)

        gridview.adapter = Adapter

        gridview.setOnItemClickListener(this)
        
        return view;
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val intent = Intent(activity, TakoyakiDetailsActivity::class.java)
        intent.putExtra("IMAGEID", imageList.get(position))
        Data.nowID = position
        startActivity(intent)
    }
}