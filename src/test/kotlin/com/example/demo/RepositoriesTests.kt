package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class RepositoriesTests(@Autowired val entiryManager: TestEntityManager,
                        @Autowired val userRepository: UserRepository,
                        @Autowired val articleRepository: ArticleRepository) {
    @Test
    fun `When findById then return Article`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        entiryManager.persist(juergen)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        entiryManager.persist(article)
        entiryManager.flush()
        val found = articleRepository.findById(article.id!!)
        assertThat(found.get()).isEqualTo(article)
    }

    @Test
    fun `When findById then return User`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        entiryManager.persist(juergen)
        entiryManager.flush()

        val found = userRepository.findById(juergen.login)

        assertThat(found.get()).isEqualTo(juergen)
    }
}
