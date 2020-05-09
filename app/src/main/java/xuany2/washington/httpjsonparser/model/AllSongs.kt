package xuany2.washington.httpjsonparser.model

data class AllSongs (
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>
)