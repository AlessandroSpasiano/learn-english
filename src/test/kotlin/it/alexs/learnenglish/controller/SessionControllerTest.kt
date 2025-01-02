package it.alexs.learnenglish.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.alexs.learnenglish.model.SessionCheckRequest
import it.alexs.learnenglish.model.SessionCheckResponse
import it.alexs.learnenglish.model.SessionRequest
import it.alexs.learnenglish.utils.asJsonString
import it.alexs.learnenglish.utils.fromJsonString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
class SessionControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    fun should_create_new_session() {
        mockMvc.post("/session") {
            content = asJsonString(SessionRequest("test"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType("application/json") }
            jsonPath("$") { isArray() }
            jsonPath("$[0].id") { isNumber() }
            jsonPath("$[0].session") { isMap() }
            jsonPath("$[0].session.user") { value("test") }
            jsonPath("$[0].session.createdAt") { isString() }
            jsonPath("$[0].irregularVerb") { isMap() }
        }

    }

    @Test
    fun should_update_session() {
        val result = mockMvc.post("/session") {
            content = asJsonString(SessionRequest("test"))
            contentType = MediaType.APPLICATION_JSON
        }.andReturn()
            .response

        val sessionCheckResponse : List<SessionCheckResponse> = fromJsonString(result.contentAsString)

        mockMvc.put("/session/${sessionCheckResponse[0].id}") {
            content = asJsonString(SessionCheckRequest(
                irregularVerbId = sessionCheckResponse[0].irregularVerb.id!!,
                answerPastSimple = "test",
                answerPastParticiple = "test"
            ))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType("application/json") }
            jsonPath("$.answerPastSimple") { value("test") }
            jsonPath("$.answerPastParticiple") { value("test") }
        }
    }

    @Test
    fun should_validate_session() {
        val result = mockMvc.post("/session") {
            content = asJsonString(SessionRequest("test"))
            contentType = MediaType.APPLICATION_JSON
        }.andReturn()
            .response

        val sessionCheckResponse : List<SessionCheckResponse> = fromJsonString(result.contentAsString)

        mockMvc.put("/session/${sessionCheckResponse[0].id}") {
            content = asJsonString(SessionCheckRequest(
                irregularVerbId = sessionCheckResponse[0].irregularVerb.id!!,
                answerPastSimple = sessionCheckResponse[0].irregularVerb.pastSimple,
                answerPastParticiple = "test"
            ))
            contentType = MediaType.APPLICATION_JSON
        }.andReturn()

        mockMvc.get("/session-verify/${sessionCheckResponse[0].id}")
            .andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$") { isMap() }
                jsonPath("$.result") { isArray() }
                jsonPath("$.result[0].answerPastSimple") { isMap() }
                jsonPath("$.result[0].answerPastParticiple") { isMap() }
                jsonPath("$.result[0].answerPastSimple.correct") { value(true) }
                jsonPath("$.result[0].answerPastParticiple.answer") { value("test") }
                jsonPath("$.result[0].answerPastParticiple.correct") { value(false) }
                jsonPath("$.result[0].answerPastParticiple.correctAnswer") { value(sessionCheckResponse[0].irregularVerb.pastParticiple) }
            }
    }
}