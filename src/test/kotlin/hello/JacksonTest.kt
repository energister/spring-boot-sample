package hello

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * How to Handle Java 8 Dates and Time with Jackson in Spring Boot (JSR-310)
 * https://codeboje.de/jackson-java-8-datetime-handling/
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class JacksonTest {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    private fun instantiateObjectMapper(): ObjectMapper {
        return ObjectMapper().findAndRegisterModules()
    }

    /**
     * WRITE_DATES_AS_TIMESTAMPS == false by default
     */
    @Test
    fun zonedDateTimeSerializedAsStringByDefault() {
        val time = ZonedDateTime.of(2020, 2, 2, 23, 38, 1, 123_000_000, ZoneId.of("Europe/Moscow"))

        val serialized = objectMapper.writeValueAsString(time)

        assertEquals(""""2020-02-02T23:38:01.123+03:00"""", serialized)
    }

    @Test
    fun zonedDateTimeSerializedAsStringWithWriteDatesAsTimeStamps() {
        val time = ZonedDateTime.of(2020, 2, 2, 23, 38, 1, 123_000_000, ZoneId.of("Europe/Moscow"))

        val mapper = instantiateObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)

        val serialized = mapper.writeValueAsString(time)

        assertEquals("1580675881.123000000", serialized)
    }

    /**
     * because ADJUST_DATES_TO_CONTEXT_TIME_ZONE == true by default
     */
    @Test
    fun zonedDateTimeDeserialization() {
        val serialized = """"2020-10-02T01:21:19.365+03:00[Europe/Moscow]""""

        val deserialized = objectMapper.readValue(serialized, ZonedDateTime::class.java)

        val expected = ZonedDateTime.of(2020, 10, 1, 22, 21, 19, 365_000_000, ZoneId.of("UTC"))
        assertEquals(expected, deserialized)
    }

    @Test
    fun zonedDateTimeDeserializationWithoutAdjustDatesToContextTimeZone() {
        val serialized = """"2020-10-02T01:21:19.365+03:00[Europe/Moscow]""""

        val mapper = instantiateObjectMapper()
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)

        val deserialized = mapper.readValue(serialized, ZonedDateTime::class.java)

        val expected = ZonedDateTime.of(2020, 10, 2, 1, 21, 19, 365_000_000, ZoneId.of("Europe/Moscow"))
        assertEquals(expected, deserialized)
    }
}