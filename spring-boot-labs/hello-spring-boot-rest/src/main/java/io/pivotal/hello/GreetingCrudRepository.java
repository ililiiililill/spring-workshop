package io.pivotal.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GreetingCrudRepository extends CrudRepository<Greeting, Integer> {

    /**
     * Find a greeting with the specified text.
     *
     * @param text
     * @return The text if found, null otherwise.
     */
    public Greeting findByText(String text);

    /**
     * Find greeting whose name contains the specified string
     *
     * @param partialText
     *            Any alphabetic string.
     * @return The list of matching greetings - always non-null, but may be
     *         empty.
     */
    public List<Greeting> findByTextContainingIgnoreCase(String partialText);


}