package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Test Find By Index: New number is saved to the repository")
    public void testFindByIndexNewNumberIsSavedInRepository() {
        FibonacciNumber number = new FibonacciNumber(1, 1);
        repository.save(number);

        entityManager.flush();
        entityManager.detach(number);

        assertEquals(1, repository.count());
    }

    @Test
    @DisplayName("Test Find by Index: returns correct value")
    public void testFindByIndexCorrectValueReturns() {
        int index = 7;
        int value = 13;
        FibonacciNumber number = new FibonacciNumber(index, value);
        repository.save(number);

        entityManager.flush();
        entityManager.detach(number);

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 7",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(number.getValue(), actual.get(0).getValue());
    }

    @Test
    @DisplayName("Test Find by Index: no exceptions or duplicates emerge")
    public void testFindByIndexNoExceptionsOrDuplicates() {
        int index = 7;
        int value = 13;
        FibonacciNumber number = new FibonacciNumber(index, value);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        repository.save(new FibonacciNumber(7, 13));
        entityManager.flush();
        entityManager.detach(number);

        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 7",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
    }

}
