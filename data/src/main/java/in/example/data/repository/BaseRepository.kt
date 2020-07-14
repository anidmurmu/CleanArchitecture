package `in`.example.data.repository

import `in`.example.data.base.DomainMapper
import org.koin.core.KoinComponent

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {

}