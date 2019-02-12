package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FnumCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFnumCommandBehavior(new String[]{"91119111"}, Arrays.asList(td.amy));

        //partial key of number: matched
        assertFnumCommandBehavior(new String[]{"9111"}, Arrays.asList(td.amy));

        //common keyword: matches multiple
        assertFnumCommandBehavior(new String[]{"9"},
                Arrays.asList(td.dan, td.candy, td.bill));

        //multiple keywords
        assertFnumCommandBehavior(new String[]{"123", "93339333", "9222"},
                Arrays.asList(td.dan, td.candy, td.bill));

        //repeated keywords: matched
        assertFnumCommandBehavior(new String[]{"91119111", "91119111"}, Arrays.asList(td.amy));

        //Keyword matching a word in address: not matched
        assertFnumCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFnumCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FnumCommand command = createFnumCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FnumCommand createFnumCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FnumCommand command = new FnumCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}