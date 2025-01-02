package it.alexs.learnenglish

import it.alexs.learnenglish.controller.IrregularVerbsController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class LearnEnglishApplicationTests(
    @Autowired
    private val irregularVerbsController: IrregularVerbsController
) {

    @Test
    fun contextLoads() {
        assertThat(irregularVerbsController).isNotNull
    }

}
