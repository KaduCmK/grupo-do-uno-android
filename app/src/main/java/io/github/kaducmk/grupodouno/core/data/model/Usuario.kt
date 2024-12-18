package io.github.kaducmk.grupodouno.core.data.model

import com.google.firebase.auth.FirebaseUser

data class Usuario (
    val uid: String? = null,
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val vitorias: List<Vitoria> = emptyList()
) {
    constructor(usuarioFirestore: FirebaseUser, vitorias: List<Vitoria>) : this(
        uid = usuarioFirestore.uid,
        displayName = usuarioFirestore.displayName ?: "",
        email = usuarioFirestore.email ?: "",
        photoUrl = usuarioFirestore.photoUrl.toString(),
        vitorias = vitorias
    )
}