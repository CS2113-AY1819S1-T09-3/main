package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.MemberCommand.EditMemberCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.item.EditItemCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemNameContainsKeywordsPredicate;
import seedu.address.model.member.NameContainsKeywordsPredicate;
import seedu.address.model.member.Person;
import seedu.address.testutil.EditItemDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_POSTALCODE_AMY = "609652";
    public static final String VALID_POSTALCODE_BOB = "504829";
    public static final String VALID_MAJOR_AMY = "Medicine";
    public static final String VALID_MAJOR_BOB = "Science";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_UNUSED = "unused";
    public static final String VALID_ITEM_NAME_APPLE = "Apple";
    public static final String VALID_ITEM_NAME_BANANA = "Banana";
    public static final String VALID_ITEM_QUANTITY_APPLE = "2";
    public static final String VALID_ITEM_QUANTITY_BANANA = "1";
    public static final String VALID_ITEM_LOCATION_APPLE = "Basket";
    public static final String VALID_ITEM_LOCATION_BANANA = "Table";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String POSTALCODE_DESC_AMY = " " + PREFIX_POSTALCODE + VALID_POSTALCODE_AMY;
    public static final String POSTALCODE_DESC_BOB = " " + PREFIX_POSTALCODE + VALID_POSTALCODE_BOB;
    public static final String MAJOR_DESC_AMY = " " + PREFIX_MAJOR + VALID_MAJOR_AMY;
    public static final String MAJOR_DESC_BOB = " " + PREFIX_MAJOR + VALID_MAJOR_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_POSTALCODE_DESC = " " + PREFIX_POSTALCODE + "6444q4"; // only
    // numbers are allowed for postalcode
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "Computer &gineering"; // '&'
    // not allowed in major
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditMemberCommand.EditPersonDescriptor DESC_AMY;
    public static final EditMemberCommand.EditPersonDescriptor DESC_BOB;
    public static final EditItemCommand.EditItemDescriptor DESC_APPLE;
    public static final EditItemCommand.EditItemDescriptor DESC_BANANA;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withPostalcode(VALID_POSTALCODE_AMY)
                .withMajor(VALID_MAJOR_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withPostalcode(VALID_POSTALCODE_BOB).withMajor(VALID_MAJOR_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_APPLE = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE)
                .withItemQuantity(VALID_ITEM_QUANTITY_APPLE).withItemLocation(VALID_ITEM_LOCATION_APPLE).build();
        DESC_BANANA = new EditItemDescriptorBuilder().withItemName(VALID_ITEM_NAME_BANANA)
                .withItemQuantity(VALID_ITEM_QUANTITY_BANANA).withItemLocation(VALID_ITEM_LOCATION_BANANA).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered member list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the member at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList().size());

        Item item = model.getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitItemName = item.getItemName().fullItemName.split("\\s+");
        model.updateFilteredItemList(new ItemNameContainsKeywordsPredicate(Arrays.asList(splitItemName[0])));

        assertEquals(1, model.getFilteredItemList().size());
    }

    /**
     * Deletes the first member in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        Person firstPerson = model.getFilteredPersonList().get(0);
        model.deletePerson(firstPerson);
        model.commitAddressBook();
    }

}
