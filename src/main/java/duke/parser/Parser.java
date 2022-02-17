package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeInvalidArgumentException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user inputs for commands.
 */
public class Parser {
    private String[] parseArguments(String[] arguments) throws DukeInvalidArgumentException {
        if (arguments.length < 2) {
            throw new DukeInvalidArgumentException("There appears to be insufficient arguments");
        }

        return arguments[1].split(" /([Aa][Tt]|[Bb][Yy]) ", 2);
    }

    private LocalDateTime parseDateTime(String datetime) {
        DateTimeFormatter datetimePattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(datetime, datetimePattern);
    }

    private int parseIndex(String strIndex) {
        return Integer.parseInt(strIndex.trim()) - 1;
    }

    /**
     * Parses user input for specified commands.
     *
     * @param userInput raw input from user.
     * @return Command parsed from user input.
     * @throws DukeInvalidArgumentException If user input invalid arguments.
     */
    public Command parseCommands(String userInput) throws DukeInvalidArgumentException {
        String[] parsedUserInput = userInput.split(" ", 2);
        String commandType = parsedUserInput[0].toLowerCase();
        String[] parsedArguments;
        String content = null;
        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseIndex(parsedUserInput[1]));
            case "unmark":
                return new UnmarkCommand(parseIndex(parsedUserInput[1]));
            case "delete":
                return new DeleteCommand(parseIndex(parsedUserInput[1]));
            case "todo":
                parsedArguments = parseArguments(parsedUserInput);
                content = parsedArguments[0];
                return new AddCommand(commandType, content, null);
            case "deadline":
            case "event":
                parsedArguments = parseArguments(parsedUserInput);
                content = parsedArguments[0];
                LocalDateTime datetime = parseDateTime(parsedArguments[1]);
                return new AddCommand(commandType, content, datetime);
            default:
                return new InvalidCommand();
        }
    }
}