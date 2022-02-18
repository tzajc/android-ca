package com.example.android_ca.feature.locations.domain

import com.example.android_ca.feature.locations.LocationRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import org.junit.jupiter.api.Test

internal class GetLocationsUseCaseTest {
    @Test
    fun `when use case is invoked than execute getLocation method from repository`() {
        //given
        val repository = mockk<LocationRepository>(relaxed = true)
        val useCase = GetLocationsUseCase(repository)
        //when
        useCase(
            params = Unit,
            scope = GlobalScope
        )
        //then
        coVerify { repository.getLocations() }
    }
}
