package io.github.kaducmk.grupodouno.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {
    @Provides
    fun provideFirestoreService(): FirestoreService =
        FirestoreService()

    @Provides
    fun provideUsuarioService(): UsuarioService =
        UsuarioService()

    @Provides
    fun provideAuthService(usuarioService: UsuarioService): AuthService =
        AuthService(usuarioService)
}