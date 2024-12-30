package it.alexs.learnenglish.controller

import it.alexs.learnenglish.service.IrregularVerbsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
    locations = ["classpath:application-integrationtest.yml"])
class IrregularVerbsControllerTest(
    @Autowired private val mocMvc: MockMvc,
    @Autowired private val irregularVerbsService: IrregularVerbsService
) {


}
