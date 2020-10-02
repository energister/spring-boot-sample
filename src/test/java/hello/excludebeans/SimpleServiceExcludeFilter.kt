package hello.excludebeans

import hello.SimpleService
import org.springframework.boot.context.TypeExcludeFilter
import org.springframework.core.type.classreading.MetadataReader
import org.springframework.core.type.classreading.MetadataReaderFactory

/**
 * For an example see
 * https://github.com/spring-projects/spring-boot/blob/2.2.x/spring-boot-project/spring-boot-test/src/main/java/org/springframework/boot/test/context/filter/TestTypeExcludeFilter.java
 */
class SimpleServiceExcludeFilter : TypeExcludeFilter() {

    override fun match(metadataReader: MetadataReader, metadataReaderFactory: MetadataReaderFactory): Boolean {
        return metadataReader.classMetadata.className == SimpleService::class.qualifiedName
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}