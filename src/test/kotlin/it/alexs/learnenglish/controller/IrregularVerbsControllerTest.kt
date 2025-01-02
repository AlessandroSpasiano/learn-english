package it.alexs.learnenglish.controller

import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
class IrregularVerbsControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    fun `should return all irregular verbs`() {
        mockMvc.get("/irregular-verbs")
            .andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$[0].baseForm") { value("be") }
                jsonPath("$[0].pastSimple") { value("was/were") }
                jsonPath("$[0].pastParticiple") { value("been") }
            }
    }

}