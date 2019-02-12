package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose number contains any of the argument keywords.
 */

public class FnumCommand extends Command {

    public static final String COMMAND_WORD = "fnum";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose number contains the numbers searched and "
            + "displays them as a list in an index manner. Put spaces between different phone numbers to be searched.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 081808 133 00";

    private final Set<String> keywords;

    public FnumCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNumberContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose number contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */

    private List<ReadOnlyPerson> getPersonsWithNumberContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final String sequenceInNumber = person.getPhone().toString();
//          using many loops might not be so efficient, hashing is better
            for (String key : keywords){

                if (sequenceInNumber.contains(key)){
                    boolean add = true;
                    for (ReadOnlyPerson p: matchedPersons) {
                        if (p.equals(person)) {
                            add = false;
                        }
                    }
                    if (add == true){
                        matchedPersons.add(person);
                    }

                }

            }
//            if (!Collections.disjoint(sequenceInNumber, keywords)) {
//                matchedPersons.add(person);
//            }
        }
        return matchedPersons;
    }

}
