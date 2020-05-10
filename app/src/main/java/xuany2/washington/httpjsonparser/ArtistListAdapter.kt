package xuany2.washington.httpjsonparser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xuany2.washington.httpjsonparser.model.Artist

class ArtistListAdapter(initListOfArtists: List<Artist>): RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder>() {

    private var listOfArtists: List<Artist> = initListOfArtists

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)

        return ArtistViewHolder(view)
    }

    override fun getItemCount() = listOfArtists.size

    override fun onBindViewHolder(holder: ArtistListAdapter.ArtistViewHolder, position: Int) {
        val artist = listOfArtists[position]
        holder.bind(artist)
    }

    inner class ArtistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val artistName = itemView.findViewById<TextView>(R.id.artistNameIndividual)
        private val artistPhoto = itemView.findViewById<ImageView>(R.id.artistSmall)

        fun bind(artist: Artist) {
            artistName.text = artist.name

            Picasso.get().load(artist.smallImageURL).into(artistPhoto)
        }
    }
}