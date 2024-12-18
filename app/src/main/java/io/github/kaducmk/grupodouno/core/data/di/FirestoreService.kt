package io.github.kaducmk.grupodouno.core.data.di

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import io.github.kaducmk.grupodouno.core.data.model.Vitoria
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreService @Inject constructor() {
    private val fb = Firebase.firestore

    suspend fun getUsuarios(): List<Usuario> {
        return try {
            val usuarios = fb.collection("users")
                .get()
                .await()

            usuarios.mapNotNull { doc ->
                val dto = doc.toObject(Usuario::class.java)
                val vitorias =
                    doc.reference.collection("vitorias").get().await().mapNotNull { vitoriaDoc ->
                        Vitoria(
                            id = vitoriaDoc.id,
                            data = vitoriaDoc.get("data", String::class.java) ?: "",
                        )
                    }

                Usuario(
                    uid = doc.id,
                    displayName = dto.displayName,
                    email = dto.email,
                    photoUrl = dto.photoUrl,
                    vitorias = vitorias
                )
            }
        } catch (e: Exception) {
            Log.e("FirestoreService", "Error getting usuarios", e)
            emptyList()
        }
    }

    suspend fun removeVitoria(usuario: Usuario, vitoriaId: String) {
        try {
            fb.collection("users")
                .document(usuario.uid!!)
                .collection("vitorias")
                .document(vitoriaId)
                .delete()
                .await()

            Log.d("FirestoreService", "Vitoria removed")
        } catch (e: Exception) {
            Log.e("FirestoreService", "Error removing vitoria", e)
        }
    }

    suspend fun addVitoria(usuario: Usuario) {
        try {
            fb.collection("users")
                .document(usuario.uid!!)
                .collection("vitorias")
                .add(mapOf("data" to "2021-09-01"))
                .await()

            Log.d("FirestoreService", "Vitoria added")
        } catch (e: Exception) {
            Log.e("FirestoreService", "Error adding vitoria", e)
        }
    }
}