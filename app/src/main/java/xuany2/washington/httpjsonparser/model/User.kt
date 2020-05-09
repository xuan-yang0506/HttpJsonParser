package xuany2.washington.httpjsonparser.model

data class User(
    val username: String,
    val firstName: String,
    val lastName: String,
    val hasNode: Boolean,
    val platform: Double,
    val profilePicURL: String
)